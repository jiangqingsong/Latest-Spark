package ScalaLearn

import com.mongodb.DBCollection
import com.mongodb.casbah.{MongoClient, MongoDB}

/**
  *
  *
  * <pre>
  * User: liuyu
  * Date: 2016/8/10
  * Time: 16:52
  * </pre>
  *
  * @author liuyu
  */
class Connection2Mongo {
	val mongoClient: MongoClient = MongoClient("192.168.1.115",8888)
	val dB: MongoDB = mongoClient.getDB("TestScala")
	val collection: DBCollection = dB.getCollection("TestData")
}
