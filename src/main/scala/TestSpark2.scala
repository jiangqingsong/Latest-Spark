import com.mongodb.casbah.commons.{Imports, MongoDBObject}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}

import scala.io.{BufferedSource, Source}

/**
  *
  *
  * <pre>
  * User: liuyu
  * Date: 2016/8/9
  * Time: 15:41
  * </pre>
  *
  * @author liuyu
  */



object TestSpark2 {
	def main(args: Array[String]) {
		val conf = new SparkConf().setAppName("Spark2-test")
		val sc =new SparkContext(conf)

		val file: RDD[String] = sc.textFile("F:/Work/Latest-Spark/src/main/resources/test.txt",2)
		val word: RDD[String] = file.flatMap(lines=>lines.split("##"))
		val wordCount: RDD[(String, Int)] = word.map(word=>(word,1))
		val key_value: RDD[(String, Int)] = wordCount.reduceByKey(_+_)
		val array: Array[(String, Int)] = key_value.collect()


		key_value.saveAsTextFile("file:///F:/Work/Latest-Spark/src/main/resources/result.txt")

		//array.foreach(_=>print(_))
		for(a<- array){
			println(a)
		}


		/*val source: BufferedSource = Source.fromFile("F:/Work/Latest-Spark/src/main/resources/test.txt","utf-8")
		val lines: Iterator[String] = source.getLines()

		val mongo: Connection2Mongo = new Connection2Mongo()

		for(line <- lines){

			val split: Array[String] = line.split("##")
			val split1: Array[String] = split(1).split("@@")

			val mongoDBObject: Imports.DBObject = MongoDBObject("key"->split(0),"IP"->split1(0),"ADDRESS"->split1(3),"URL"->split1(4))

			mongo.collection.insert(mongoDBObject)

			println(line)
		}*/

	}

}
