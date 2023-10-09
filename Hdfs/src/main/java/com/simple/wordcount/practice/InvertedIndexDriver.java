package com.simple.wordcount.practice;

import com.simple.wordcount.invertedindex.invertedIndex.Map;
import com.simple.wordcount.invertedindex.invertedIndex.Reduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class InvertedIndexDriver {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        if(args.length != 2) {
            new Exception("Please pass 2 arguments");
        }
        Configuration configuration= new Configuration();
        Job job = new Job(configuration, "Inverted Index MR job");
        job.setJarByClass(InvertedIndexDriver.class);
        //job.setCombinerClass(IIMapper.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        FileInputFormat.addInputPath(job ,new Path(args[0]));
        FileInputFormat.setInputDirRecursive(job, true);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        System.exit(job.waitForCompletion(true)  ? 0 : 1);
    }
    
}
