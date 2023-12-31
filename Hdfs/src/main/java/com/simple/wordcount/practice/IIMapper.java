package com.simple.wordcount.practice;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class IIMapper extends Mapper<LongWritable, Text, Text, Text> {


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        FileSplit currentSplit = (FileSplit) context.getInputSplit();
        String filePath =currentSplit.getPath().getName();
        for( String word :value.toString().split(" ")) {
            context.write(new Text(word), new Text(filePath));
        }
    }
}
