package Flink

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time

/**
  *
  *
  * <pre>
  * User: liuyu
  * Date: 2016/8/22
  * Time: 9:38
  * </pre>
  *
  * @author liuyu
  */

//case class WordWithCount(word: String, count: Long)

/*object FlinkStreamTest extends  App{

	val host="192.168.1.107"
	val port=9000
	val env = ExecutionEnvironment.getExecutionEnvironment

	val text = env.socketTextStream(host, port, '\n')

	val windowCounts = text.flatMap { w => w.split("\\s+") }
			.map { w => WordWithCount(w, 1) }
			.keyBy("word")
			.timeWindow(Time.seconds(5))
			.sum("count")

	windowCounts.print()
}*/
