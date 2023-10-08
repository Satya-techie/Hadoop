package com.simple.wordcount.practice;



import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WCMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        
        for(String word : value.toString().split(" ")) {
            if(word.length() > 0) {
                context.write(new Text(word), new IntWritable(1));
            }
        }
        
        
    }
}
