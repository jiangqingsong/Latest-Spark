package ScalaLearn

import java.io.{File, FileWriter, IOException}
import java.util

import org.apache.commons.io.FileUtils

import scala.io.{BufferedSource, Source}

/**
  *
  *
  * <pre>
  * User: liuyu
  * Date: 2016/8/17
  * Time: 15:21
  * </pre>
  *
  * @author liuyu
  */
class ScalaFileUtil {
	/**
	  * 读取文件的所有行
	  *
	  * @param filePath  文件路径
	  * @throws java.io.IOException
	  * @return  List
	  */
	@throws(classOf[IOException])
	def readFileAllLines(filePath:String):List[String] ={

		 val file : BufferedSource = Source.fromFile(filePath,"utf-8")
		 val lines : Iterator[String] = file.getLines()

		 file.close

		 lines.toList
	}

	/**
	  * 追加写文件
	  *
	  * @param fileName
	  * @param data
	  * @throws java.io.IOException
	  */
	@throws(classOf[IOException])
	def writeFileApend(fileName: String, data: String): Unit ={
		 var fileWriter: FileWriter = null
		 fileWriter = new FileWriter(fileName, true)
		 WriteToFile(fileWriter,data + "\n")
		 fileWriter.close
	}

	@throws(classOf[IOException])
	def WriteToFile(fileWriter: FileWriter,context: String ) {
		 fileWriter.write(context)
		 fileWriter.flush
	}

	@throws(classOf[IOException])
	def  writeCollectionsToFile(fileName: String, data: util.Collection[String], split: String)  = {
		FileUtils.writeLines(new File(fileName), data, split)
	}

}
