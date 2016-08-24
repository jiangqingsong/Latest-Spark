package Flink;

/**
 * <pre>
 * User: liuyu
 * Date: 2016/8/23
 * Time: 9:42
 * </pre>
 *
 * @author liuyu
 */

//使用Flink从kafka读取数据，即Flink作为kafka的数据消费端

import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import java.util.HashMap;
import java.util.Map;

/**
 * Read Strings from Kafka and print them to standard out.
 * Note: On a cluster, DataStream.print() will print to the TaskManager's .out file!
 *
 * Please pass the following arguments to run the example:
 * 	--topic test --bootstrap.servers localhost:9092 --zookeeper.connect localhost:2181 --group.id myconsumer
 *
 */
public class FlinkReadDataFromKafka {
    public static void main(String[] args) throws Exception {
        final String topic = "Flink-test";  //主题
        final String kafkaServers = "192.168.1.101:9092";   //kafka brokers
        final String zookeeperServers = "192.168.1.101:2181";  //zk服务
        final String groupId = "FlinkConsumerDroup";    //消费者所属组Id

        Map<String, String> map = new HashMap<String, String>();
        map.put("topic",topic);
        map.put("bootstrap.servers",kafkaServers);
        map.put("zookeeper.connect",zookeeperServers);
        map.put("group.id",groupId);

        final ParameterTool parameterTool = ParameterTool.fromMap(map);

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().disableSysoutLogging();
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4, 10000));
        env.enableCheckpointing(5000); // create a checkpoint every 5 secodns
        env.getConfig().setGlobalJobParameters(parameterTool);

        DataStream<String> source = env.addSource(new FlinkKafkaConsumer08<String>(
                parameterTool.getRequired("topic"),
                new SimpleStringSchema(),
                parameterTool.getProperties()));


        source.print();

        env.execute("FlinkReadDataFromKafka");
    }
}
