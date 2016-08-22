package Flink

import org.apache.flink.api.scala.ExecutionEnvironment

/**
  *
  *
  * <pre>
  * User: liuyu
  * Date: 2016/8/22
  * Time: 9:34
  * </pre>
  *
  * @author liuyu
  */


//定义样例类，进行模式匹配
case class WordWithCount(word: String, count: Long)

object FlinkBatchTest extends App{

	val input="file:///F:/Work/Latest-Spark/src/main/resources/lppz-act.txt"
	val output="file:///F:/Work/Latest-Spark/src/main/resources/flink-test-scala.csv"

	/*
	// set up the execution environment
	val env = ExecutionEnvironment.createLocalEnvironment()
	val text = env.readTextFile(input)

	val counts = text.flatMap { w => w.split("\\s") }
			.map { w => WordWithCount(w, 1) }
			.groupBy("word")
			.sum("count")

	counts.writeAsCsv(output)
	*/

/*

	// set up execution environment
	val env = ExecutionEnvironment.getExecutionEnvironment

	val text =env.readTextFile(input)

	val counts = text.flatMap { _.toLowerCase.split("\\s+")}
			.map { (_, 1) }
			.groupBy(0)
			.sum(1)

	counts.writeAsCsv(output, "\n", " ")
	env.execute("Scala WordCount Example")
*/

}
