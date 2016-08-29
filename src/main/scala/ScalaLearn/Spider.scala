package ScalaLearn

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

/**
  *
  *
  * <pre>
  * User: liuyu
  * Date: 2016/8/26
  * Time: 14:19
  * </pre>
  *
  * @author liuyu
  */

class Spider{

	def get(url:String) {
		val client = HttpClients.createDefault()
		try {
			val httpget = new HttpGet(url)
			//httpget.getURI.get

			val response = client.execute(httpget)

			val entity = response.getEntity
			val doc = Jsoup.parse(EntityUtils.toString(entity))

			//打印所有文本
			println(doc.body.text())

			//打印所有链接

			doc.select("a").toArray().map { x =>if(x!=null) println(x.asInstanceOf[Element].attr("abs:href")) }

		} catch {
			case e: Exception =>e.printStackTrace()
		}
	}
}

object Spider extends App {
	//目标Url

	val urlstring = "http://weibo.cn/zhouhongyi?vt=4&page="

	val s = new Spider
	//爬取第0到9页

	for(i<-0 to 9){

		val start = System.currentTimeMillis();
		s.get(urlstring+i)
		Thread.sleep(5000)
	}
}
