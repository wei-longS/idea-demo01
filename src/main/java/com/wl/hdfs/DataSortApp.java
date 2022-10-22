package com.wl.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 数据排序
 */
public class DataSortApp {

    public static class Map extends Mapper<Object, Text, IntWritable, IntWritable> {
        protected void map(Object key, Text value,
                           Context context) throws IOException, InterruptedException {
            StringTokenizer tokenizer = new StringTokenizer(value.toString(), "\n");
            while (tokenizer.hasMoreElements()) {
                IntWritable data = new IntWritable(Integer.parseInt(tokenizer.nextToken()));
                context.write(data, new IntWritable(1));
            }
        }
    }

    public static class Reduce extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
        private static IntWritable lineNum = new IntWritable(1);

        @SuppressWarnings("unused")
        protected void reduce(IntWritable key, Iterable<IntWritable> values,
                              Context context) throws IOException, InterruptedException {
            for (IntWritable val : values) {
                context.write(lineNum, key);
                lineNum.set(lineNum.get() + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String input = "/user/root/datasort/input",
                output = "/user/root/datasort/output";
        System.setProperty("HADOOP_USER_NAME", "root");
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.56.202:9000");

        FileSystem fs = FileSystem.get(conf);
        boolean exists = fs.exists(new Path(output));
        if (exists) {
            fs.delete(new Path(output), true);
        }

        Job job = Job.getInstance(conf);
        job.setJarByClass(DataSortApp.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}
