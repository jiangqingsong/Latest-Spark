import Services.SendData2Kafka;

/**
 * <pre>
 * User: liuyu
 * Date: 2016/8/11
 * Time: 18:11
 * </pre>
 *
 * @author liuyu
 */
public class MySendData2Kafka {

    public static void main(String[] args) {
        SendData2Kafka producer = null;
        try{
            producer = new SendData2Kafka();
            int i=0;
            while(true){
                producer.send("yaxin-test", ("Hello,my to kafka ..." + i));

                System.out.println(i);
                i++;
                Thread.sleep(100);

            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(producer != null){
                producer.close();
            }
        }

    }
}
