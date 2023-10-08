package com.simple.wordcount.practice;

import com.simple.wordcount.program.WordCountMapper;
import com.simple.wordcount.program.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WCDriver {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        
        if(args.length != 2) {
            
            new Exception("Please enter Input file and output arguments");
        }

        Configuration configuration = new Configuration();
        Job job = new Job(configuration, "WC program");
        job.setJarByClass(WCDriver.class);
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WCReducer.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setMapperClass(WCMapper.class);
        job.setCombinerClass(WCReducer.class);
        job.setReducerClass(WCReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
        
        
        
    }
    
    
}
