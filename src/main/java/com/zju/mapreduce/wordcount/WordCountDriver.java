package com.zju.mapreduce.wordcount;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.MapFileOutputFormat;

import java.io.IOException;

/**
 * @Author Yifan Wu
 * Date on 2022/1/13  11:48
 */
public class WordCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // configuration
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        // set jar,mapper,reduce etc
        job.setJarByClass(WordCountDriver.class);
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        //set map output
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //set output type
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //set input path
        FileInputFormat.setInputPaths(job,new Path("D:\\hadoop\\input\\inputword"));
        MapFileOutputFormat.setOutputPath(job,new Path("D:\\hadoop\\output"));
        //submit the job
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);

    }
}
