package com.zju.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author Yifan Wu
 * Date on 2022/1/13  11:48
 */
public class WordCountMapper extends Mapper<LongWritable,Text,Text,IntWritable> {

    private Text keyout = new Text();
    private IntWritable valueout = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] words = line.split(" ");
        // content is the output
        for(String word : words){
            keyout.set(word);
            context.write(keyout,valueout);
        }
    }
}
