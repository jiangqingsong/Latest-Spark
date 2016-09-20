package Redis

import akka.actor.ActorRef
import redis.RedisClient
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
  * <pre>
  * User: liuyu
  * Date: 2016/8/24
  * Time: 15:38
  * </pre>
  *
  * @author liuyu
  */

/**
  * 使用开源的redisscala项目，写数据到redis
  */
/*object RedisTest {

	private val redisServer: String = "192.168.1.115"
	private val port: Int = 9999
	private val password: Option[String] = Some("123")
	private val dbindex: Option[Int] = Some(0)

	def main(args: Array[String]) {
		implicit val system = akka.actor.ActorSystem()
		val redis = RedisClient(host = redisServer, port = port, password = password, db = dbindex)
		val futurePong: Future[String] = redis.ping()
		println("Ping sent!")
		futurePong.map(pong => {
			println(s"Redis replied with a $pong")
		})
		Await.result(futurePong, 5 seconds)

		//system.shutdown()
		system.stop(null)

	}
}*/

