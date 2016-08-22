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
object test extends App{
	val intArr = Array(1,2,3,4,5,6,7,8,9,10)
	var multiDimArr=Array(Array(1,2,3),Array(2,3,4))

	println(intArr.mkString(","))

	for(i <- multiDimArr){
		println(i.size)
		for(j <- i){
			println(j)
		}
		println( i.mkString(","))
	}


}
