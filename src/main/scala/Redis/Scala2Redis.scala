package Redis

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.slf4j.LoggerFactory
import redis.RedisClient

/**
  * <pre>
  * User: liuyu
  * Date: 2016/8/24
  * Time: 13:46
  * </pre>
  *
  * @author liuyu
  */

/**
  * 行为
  * @param uid 手机uid
  * @param time 点击时间
  * @param os 操作系统型号
  * @param click 点击次数
  */
case class Event(uid: Option[String], time: Long, os: Option[String], click: Int)

object Scala2Redis {
	/**
	  * redis 的隐式参数
	  */
	implicit val system = akka.actor.ActorSystem()
	/**
	  * json的隐式参数
	  */
	implicit val formats = DefaultFormats
	/**
	  *  由于streaming中的东西都需要可序列化，所以放在主函数外面，并进行迟加载
	  *  redis客户端
	  *  val password: Option[String] = Some("Hello world")
	  *  val password: Option[String] = None
	*/
	lazy val redisClient = RedisClient(host = "127.0.0.1",port =9999,password = Some("123"),db = Some(0))

	/**
	  * 日志
	  */
	val log = LoggerFactory.getLogger(this.getClass)

	def main(args: Array[String]) {
		/**
		  * 设置masterUrl
		  */
		val masterUrl = if (args.length > 0) {
			args(0)
		}else {
			"local[2]"
		}

		/**
		  * spark和kafka的相关配置设置
		  */
		val conf = new SparkConf().setMaster(masterUrl).setAppName("UserClickCount")
		val ssc = new StreamingContext(conf, Seconds(5))
		val topics = Set("click_events")
		val brokers = "192.168.1.101:9092"
		val kafkaParams = Map[String,String]("metadata.broker.list"->brokers,"serializer.class" ->"kafka.serializer.StringEncoder")
		val chickHashKey = "app::users::click"

		/**
		  * 创建direct stream
		  */
		val kafkaStream = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](ssc,kafkaParams,topics)

		/**
		  * 点击事件，json转case class
		  */
		val events = kafkaStream.flatMap(line => {
			val data = parse(line._2)
			val event = data.extract[Event]
			Some(event)
		})

		/**
		  * 构建基于uid和点击数的元组，并对key值用累加方式计算，将结果存入redis
		  */
		val userClicks = events.map { event => (event.uid.getOrElse("null"), event.click)
		}.reduceByKey(_ + _)
		userClicks.foreachRDD { rdd => {
			rdd.foreachPartition(partitionOfRecords => {
				partitionOfRecords.foreach(pair => {
					val uid = pair._1
					val clickCount = pair._2
					redisClient.hincrby(chickHashKey, uid, clickCount)
					log.info(s"$uid:$clickCount")
				}
				)
			})
		}
		}

		ssc.start()
		ssc.awaitTermination()

	}
}
