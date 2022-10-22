//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapred.FileInputFormat;
//import org.apache.hadoop.mapred.FileOutputFormat;
//import org.apache.hadoop.mapreduce.Job;
//
//import javafx.scene.text.Text;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.mapred.FileInputFormat;
//import org.apache.hadoop.mapred.FileOutputFormat;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.conf.Configuration;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//
//import java.io.IOException;
//
//public class MaxTemperature {
//    public static  void main(String[] args) throws IOException {
//        if (args.length !=2){
//            System.out.println("Usage:MaxTemperature <input path> <out path>");
//            System.exit(-1);
//        }
//        Configuration config = new Configuration();
//        Job job = Job.getInstance (config);
////        Job job = new Job();
//        job.setJarByClass(MaxTemperature.class);
//        job.setJobName("Max Temperature");
//        //路径可以是单个文件，也可以是一个目录或符合特定文件模式的一系列文件
//        //可多次调用实现多路径输入
//        FileInputFormat.addInputPath(job,new Path(args[0]));
//        //输出前目录应该是不存在的
//        FileOutputFormat.setOutputPath(job,new Path(args[1]);
//        job.setMapperClass(MaxTemperatureMapper.class);
//        job.setReducerClass(MaxTemperatureReducer.class);
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(IntWritable.class);
//        try {
//            System.exit(job.waitForCompletion(true) ? 0 : 1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
