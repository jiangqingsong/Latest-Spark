package Services;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * <pre>
 * User: liuyu
 * Date: 2016/8/11
 * Time: 17:57
 * </pre>
 *
 * @author liuyu
 */


//发送数据到kafka
public class SendData2Kafka {
    private Producer<String,String> inner;
    //private KafkaProducer<String, String> kafkaProducer;

    public SendData2Kafka() throws Exception{
        Properties properties = new Properties();

        properties.load(ClassLoader.getSystemResourceAsStream("producer.properties"));
        ProducerConfig config = new ProducerConfig(properties);
        inner = new Producer<String, String>(config);

        /*Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.101:9092");
        props.put("client.id", "DemoProducer");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        kafkaProducer = new KafkaProducer<String, String>(props);*/
    }


    public void send(String topicName,String message) {
        if(topicName == null || message == null){
            return;
        }
        KeyedMessage<String, String> km = new KeyedMessage<String, String>(topicName,message);

        inner.send(km);
        //ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, message);
        //kafkaProducer.send(record);
    }

    public void send(String topicName,Collection<String> messages) {
        if(topicName == null || messages == null){
            return;
        }
        if(messages.isEmpty()){
            return;
        }
        List<KeyedMessage<String, String>> kms = new ArrayList<KeyedMessage<String, String>>();
        for(String entry : messages){
            KeyedMessage<String, String> km = new KeyedMessage<String, String>(topicName,entry);
            kms.add(km);
        }
        inner.send(kms);
    }

    public void close(){
        inner.close();
    }
}
