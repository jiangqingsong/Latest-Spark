package ScalaLearn

/**
  *
  *
  * <pre>
  * User: liuyu
  * Date: 2016/8/22
  * Time: 14:04
  * </pre>
  *
  * @author liuyu
  */
object  CaseClassTest{
	val regex="""([0-9]+) ([a-z]+)""".r  //定义正则表达式对象
	def add(a:Int,b:Int): Int ={
		a + b
	}

	def cheng=(a:Int,b:Int)=>a*b

	def matchTest(myInput:String,arg1:Int,arg2:Int): Unit ={
		myInput match{
			case "+" => {
				val res=add(arg1,arg2)
				println("执行加法操作："+res)
			}

			case "*" => {
				val res=cheng(arg1,arg2)
				println("执行乘法操作："+res)
			}
			case "-" => println((arg1-arg2))

			case "/" => println((arg1/arg2))

			case "%" => println((arg1%arg2))

			case _ => println("Scala 模式匹配！！！")
		}
	}

	def main(args:Array[String]): Unit ={
		matchTest("",25,36)
	}
}
