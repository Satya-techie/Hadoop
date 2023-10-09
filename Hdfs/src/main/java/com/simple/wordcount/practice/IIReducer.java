package com.simple.wordcount.practice;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class IIReducer extends Reducer<Text, Text, Text, Text> {


    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {


        StringBuilder stringBuilder= new StringBuilder();
        values.forEach(text -> {
            if(values.iterator().hasNext()) {
              stringBuilder.append(text).append(" | ").toString();
            }
             stringBuilder.append(text).toString();
        });
        
        context.write(key, new Text(stringBuilder.toString()));
        
        
        
        
    }
}
