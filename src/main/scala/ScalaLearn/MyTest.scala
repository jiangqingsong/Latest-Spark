package ScalaLearn

import java.util

/**
  *
  *
  * <pre>
  * User: liuyu
  * Date: 2016/8/15
  * Time: 10:26
  * </pre>
  *
  * @author liuyu
  */
object MyTest extends App{


	private val scalaFileUtil: ScalaFileUtil = new ScalaFileUtil()

	private val allLines: List[String] = scalaFileUtil.readFileAllLines("F:/Work/Latest-Spark/src/main/resources/lppz-act.txt")

	//private val readLines: util.List[String] = FileUtil.readLines("F:/Work/Latest-Spark/src/main/resources/lppz-act.txt")

	for(line <- allLines){
		val split: Array[String] = line.toString.split("\t")
		if(split.length == 4){
			val fileApend: Unit = scalaFileUtil.writeFileApend("F:/Work/Latest-Spark/src/main/resources/scala-test.txt",split(0))
		}
		println(line)
	}

}
