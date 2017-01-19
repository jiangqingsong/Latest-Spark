package KafkaTest;


import kafka.producer.Partitioner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * User: liuyu
 * Date: 2016/9/28
 * Time: 16:15
 * </pre>
 *
 * @author liuyu
 */

/**
 * 自定义kafka发送数据时发送到那个分区（自定义分区策略），需实现Partitioner
 */
public class ProducerPartitioner implements Partitioner{

    public static final Logger LOG=LoggerFactory.getLogger(ProducerPartitioner.class);

    public int partition(Object key, int numPartitions) {
        String mykey = (String)key;
        LOG.info("ProducerPartitioner key:"+key+" partitions:"+numPartitions);
        return mykey.length() % numPartitions;
    }
}
