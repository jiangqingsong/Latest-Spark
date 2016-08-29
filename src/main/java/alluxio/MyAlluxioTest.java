package alluxio;

import alluxio.client.file.FileOutStream;
import alluxio.client.file.FileSystem;
import alluxio.client.file.options.CreateFileOptions;
import alluxio.exception.AlluxioException;

import java.io.IOException;

/**
 * <pre>
 * User: liuyu
 * Date: 2016/8/26
 * Time: 10:20
 * </pre>
 *
 * @author liuyu
 */
public class MyAlluxioTest {
    public static void main(String[] args) throws AlluxioException{

        FileSystem fs = FileSystem.Factory.get();
        AlluxioURI path = new AlluxioURI("/myFile");
        // Generate options to set a custom blocksize of 128 MB
        CreateFileOptions options = CreateFileOptions.defaults().setBlockSizeBytes(128 * Constants.MB);
        try{
            FileOutStream out = fs.createFile(path, options);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}
