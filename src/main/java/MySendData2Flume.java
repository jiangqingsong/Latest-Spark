import Services.SendData2Flume;

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
    public static void main(String[] args) {
        SendData2Flume client1 = new SendData2Flume();
        //SendData2Flume client2 = new SendData2Flume();

        // Initialize client with the remote Flume agent's host and port
        client1.init("192.168.1.236", 41414);
        //client2.init("192.168.1.115", 41415);

        // Send 10 events to the remote Flume agent. That agent should be
        // configured to listen with an AvroSource.
        String Data1 = "Hello Flume 1!!!";
        String Data2 = "Hello Flume 2!!!";

        for (int i = 0; i < 100; i++) {
            client1.sendDataToFlume(Data1);
           //client2.sendDataToFlume(Data2);
            try{
                Thread thread = Thread.currentThread();
                thread.sleep(2000);    //暂停程序后继续执行
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        client1.cleanUp();
        //client2.cleanUp();
    }
}