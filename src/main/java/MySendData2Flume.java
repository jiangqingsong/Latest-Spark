import Services.SendData2Flume;
import com.sdyc.ndmp.protobuf.util.AudienceIdUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * <pre>
 * User: liuyu
 * Date: 2016/8/11
 * Time: 17:01
 * </pre>
 *
 * @author liuyu
 */

public class MySendData2Flume {
    public static void main(String[] args) throws InterruptedException {
        SendData2Flume client1 = new SendData2Flume();
        SendData2Flume client2 = new SendData2Flume();

        // Initialize client with the remote Flume agent's host and port
        //client1.init("192.168.1.236", 41414);
        client2.init("192.168.1.107", 41414);

        // Send 10 events to the remote Flume agent. That agent should be
        // configured to listen with an AvroSource.
        //String Data1 = "Hello Flume,my to ...";
        String Data2 = "【LY】Hello Flume,my come from 192.168.102.35 !!!";
        long count=0;


       while (true){

           /*String uuid1 = UUIDGenerator.getUUID();
           String uuid2 = UUIDGenerator.getUUID();

           String dmpId = AudienceIdUtils.createDmpId(uuid1, 201);
           String qiHuId = DigestUtils.md5Hex(uuid2);
           long time = System.currentTimeMillis();

           StringBuilder stringBuilder = new StringBuilder(dmpId);
           stringBuilder.append("#").append("234").append("#").append(qiHuId).append("#").append(time);

           client1.sendDataToFlume(stringBuilder.toString());*/

           //client2.sendDataToFlume(Data1);

           client2.sendDataToFlume(Data2);
           Thread.sleep(300L);
           System.out.println(count++);

           /*if (count == 200) {
               break;
           }*/
        }

        //client1.cleanUp();
        //client2.cleanUp();
    }
}