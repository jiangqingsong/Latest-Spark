import com.sdyc.ndmp.protobuf.util.AudienceIdUtils;

import java.io.IOException;
import java.util.List;

/**
 * <pre>
 * User: liuyu
 * Date: 2016/8/17
 * Time: 14:34
 * </pre>
 *
 * @author liuyu
 */
public class MyLppzTest {

    public static void main(String[] args) {

        Long count=0L;
        try {
            final List<String> lines = FileUtil.readLines("F:/Work/Latest-Spark/src/main/resources/lppz-act.txt");
            for(Object line: lines){
                final String[] keyAndValue = line.toString().split("\t");
                String key = keyAndValue[0];
                String dmpId = AudienceIdUtils.createDmpId(key, 201);

                count++;
                System.out.println(dmpId);
                FileUtil.writeToFileAppendLine("F:/Work/Latest-Spark/src/main/resources/lppz-act-result.txt",dmpId);
            }

            System.out.println(count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
