package Flink;

/**
 * <pre>
 * User: liuyu
 * Date: 2016/8/22
 * Time: 15:03
 * </pre>
 *
 * @author liuyu
 */
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.core.fs.FileSystem.WriteMode;
import org.apache.flink.util.Collector;

/**
 * This example shows an implementation of WordCount without using the
 * Tuple2 type, but a custom class.
 */

public class FlinkWordCount {

    //定义类，封装统计结果
    public static class Word {

        // fields
        private String word;
        private int frequency;

        // constructors
        public Word() {}

        public Word(String word, int i) {
            this.word = word;
            this.frequency = i;
        }

        // getters setters
        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return "Word="+word+" freq="+frequency;
        }
    }


    public static void main(String[] args) throws Exception {

        String input="file:///F:/Work/Latest-Spark/src/main/resources/lppz-act.txt";
        String output="file:///F:/Work/Latest-Spark/src/main/resources/flink-test-java.txt";

        // set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        //批处理DataSet    流处理SataStream
        // get input data
        DataSet<String> text = env.readTextFile(input);

        DataSet<Word> counts =
                // split up the lines into Word objects (with frequency = 1)
                text.flatMap(new Tokenizer())
                        // group by the field word and sum up the frequency
                        .groupBy("word")
                        .reduce(new ReduceFunction<Word>() {
                            public Word reduce(Word value1, Word value2) throws Exception {
                                return new Word(value1.word,value1.frequency + value2.frequency);
                            }
                        });


        counts.writeAsText(output, WriteMode.OVERWRITE);
        // execute program
        env.execute("FlinkWordCount");


    }

    /**
     * Implements the string tokenizer that splits sentences into words as a user-defined
     * FlatMapFunction. The function takes a line (String) and splits it into
     * multiple Word objects.
     */
    public static final class Tokenizer implements FlatMapFunction<String, Word> {

        public void flatMap(String value, Collector<Word> out) {
            // normalize and split the line
            String[] tokens = value.toLowerCase().split("\\s+");

            // emit the pairs
            for (String token : tokens) {
                if (token.length() > 0) {
                    out.collect(new Word(token, 1));
                }
            }
        }
    }

}