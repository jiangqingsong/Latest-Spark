package kafka

import java.util.Properties
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.JsonDSL._
import kafka.producer.{KeyedMessage, Producer, ProducerConfig}
import org.slf4j.LoggerFactory

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
  *
  *
  * <pre>
  * User: liuyu
  * Date: 2016/8/24
  * Time: 14:51
  * </pre>
  *
  * @author liuyu
  */
object KafkaEventProducer {

	/**
	  * uid集合
	  */
	private val buffer = new ArrayBuffer[String]()
	(1  to  10) foreach (x => buffer += java.util.UUID.randomUUID().toString)

	/**
	  * 操作系统集合
	  */
	private val os = Array("ios", "android", "wp")

	private val random = new Random()


	/**
	  * 获取uid
	  *
	  * @return uid
	  */
	def getUserId: String = buffer(random.nextInt(buffer.length))


	/**
	  * 获取操作系统
	  *
	  * @return os
	  */
	def getOs: String = os(random.nextInt(os.length))

	/**
	  * 获得随机点击次数
	  *
	  * @return 随机Int
	  */
	def click = random.nextInt(10)

	/**
	  * 这个是Json中需要的一个隐式参数
	  */
	implicit val formats = DefaultFormats


	def main(args: Array[String]) {
		/**
		  * 日志
		  */
		val logger = LoggerFactory.getLogger(this.getClass)

		/**
		  * 主题
		  */
		val topic = "click_events"
		/**
		  * kafka集群
		  */
		val brokers = "192.168.1.101:9092"
		/**
		  * kafka相关配置参数
		  */
		val props = new Properties()
		props.put("metadata.broker.list", brokers)
		props.put("serializer.class", "kafka.serializer.StringEncoder")

		val kafkaConfig = new ProducerConfig(props)
		val producer = new Producer[String, String](kafkaConfig)

		/**
		  * 生产消息
		  */
		while (true) {
			/**
			  * 构建json
			  */
			val myJson =
				("uid" -> getUserId) ~
						("time" -> System.currentTimeMillis()) ~
						("os" -> getOs) ~
						("click" -> click)

			val event = compact(render(myJson))

			/**
			  * 发送到kafka集群
			  */
			producer.send(new KeyedMessage[String, String](topic, event))
			logger.info(s"Message sent:$event")

			/**
			  * 休眠200
			  */
			Thread sleep 200
		}


	}
}