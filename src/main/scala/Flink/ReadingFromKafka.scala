package Flink

import java.util.Properties

import org.apache.flink.streaming.api.{CheckpointingMode, TimeCharacteristic}
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08
import org.apache.flink.streaming.util.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._

/**
  *
  * <pre>
  * User: liuyu
  * Date: 2016/8/23
  * Time: 12:10
  * </pre>
  *
  * @author liuyu
  */
class ReadingFromKafka {
}

object ReadingFromKafka {

	private val TOPIC = "Flink-test";
	private val ZOOKEEPER_HOST = "192.168.1.101:2181"
	private val KAFKA_BROKER = "192.168.1.101::9092"
	private val CONSUMER_GROUP = "FlinkTestGroup"

	def main(args : Array[String]){
		val env = StreamExecutionEnvironment.getExecutionEnvironment
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
		env.enableCheckpointing(1000)
		env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)

		// configure Kafka consumer
		val kafkaProps = new Properties()
		kafkaProps.setProperty("zookeeper.connect", ZOOKEEPER_HOST)
		kafkaProps.setProperty("bootstrap.servers", KAFKA_BROKER)
		kafkaProps.setProperty("group.id", CONSUMER_GROUP)

		//topicd的名字是Flink-test，schema默认使用SimpleStringSchema()即可
		val transaction = env
				.addSource(
					new FlinkKafkaConsumer08[String](TOPIC, new SimpleStringSchema(), kafkaProps)
				)

		transaction.print()

		env.execute()

	}

}