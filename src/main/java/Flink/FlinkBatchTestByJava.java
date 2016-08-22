package Flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.LocalEnvironment;
import org.apache.flink.api.java.operators.*;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

import java.util.List;

/**
 * <pre>
 * User: liuyu
 * Date: 2016/8/22
 * Time: 9:41
 * </pre>
 *
 * @author liuyu
 */
public class FlinkBatchTestByJava {
    public static void main(String[] args) throws Exception{
        String input="file:///F:/Work/Latest-Spark/src/main/resources/lppz-act.txt";
        String output="file:///F:/Work/Latest-Spark/src/main/resources/flink-test-java.csv";

        LocalEnvironment env = ExecutionEnvironment.createLocalEnvironment();
        DataSet<String> textFile = env.readTextFile(input, "utf-8");

        DataSet<Tuple2<String, Integer>> counts=textFile.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] tokens = value.toLowerCase().split("\\s+");

                // emit the pairs
                for (String token : tokens) {
                    if (token.length() > 0) {
                        out.collect(new Tuple2<String, Integer>(token, 1));
                    }
                }
            }
        }).groupBy(0).sum(1);

        counts.writeAsCsv(output, "\n", " ");
        // execute program
        env.execute("WordCount-Example");


        /*
        Configuration configuration = new Configuration();
        LocalEnvironment localEnvironment = new LocalEnvironment(configuration);
        DataSource<String> stringDataSource = localEnvironment.readTextFile(input);
        FlatMapOperator<String, String> flatMapOperator = stringDataSource.flatMap(new FlatMapFunction<String, String>() {
            public void flatMap(String value, Collector<String> out) throws Exception {
                String regex = "\\s+";
                String[] split = value.split(regex);
                out.collect(split.toString());
            }
        });
        MapOperator<String, Tuple2<String, Integer>> map = flatMapOperator.map(new MapFunction<String, Tuple2<String, Integer>>() {
            public Tuple2<String, Integer> map(String value) throws Exception {

                return new Tuple2<String, Integer>(value, 1);
            }
        });

        ReduceOperator<Tuple2<String, Integer>> reduce = map.reduce(new ReduceFunction<Tuple2<String, Integer>>() {
            public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1, Tuple2<String, Integer> value2) throws Exception {

                Tuple2<String, Integer> tuple2 = new Tuple2<String, Integer>();
                if (value1.f0.equalsIgnoreCase(value2.f0.toString())) {
                    tuple2.setFields(value1.f0, value1.f1 + value2.f1);
                } else {
                    return null;
                }
                return tuple2;
            }
        });

        List<Tuple2<String, Integer>> collect = reduce.collect();

        for(Tuple2<String, Integer> tuples: collect){
            System.out.println(tuples.f0+":"+tuples.f1);

        }*/

    }
}
