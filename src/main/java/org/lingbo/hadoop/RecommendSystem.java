package org.lingbo.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class RecommendSystem {
	
    public static void main(String[] args) throws Exception {
        if(args.length < 7) {
        	throw new Exception("Usage: "
        			+ "<job1 input dir> "
        			+ "<job1 output dir> "
        			+ "<job2 output dir> "
        			+ "<job3 output dir> "
        			+ "<job4 output dir> " 
        			+ "<job5 output dir> " 
        			+ "<job6 output dir>");
        }
    	
    	Configuration conf = new Configuration();
    	conf.set("denominator", args[3] + "/part-r-00000");  			// TODO, may have multiple files
        conf.set("columnAsKeyCoocurrence", args[4] + "/part-r-00000"); 	// TODO, may have multiple files
        conf.set("output1", args[1] + "/part-r-00000");
        
        Job job1 = Job.getInstance(conf); // UserMovie
        Job job2 = Job.getInstance(conf); // Coocurrence
        Job job3 = Job.getInstance(conf); // Denominator
        Job job4 = Job.getInstance(conf); // ColumnAsKeyCoocurrence
        Job job5 = Job.getInstance(conf); // UserMovie_Score
        Job job6 = Job.getInstance(conf); // User_MovieScore
        
        job1.setMapperClass(UserMoviesMapper.class);
        job1.setReducerClass(UserMoviesReducer.class);
        job1.setJarByClass(RecommendSystem.class);
        
        job1.setInputFormatClass(TextInputFormat.class);
        job1.setOutputFormatClass(TextOutputFormat.class);
        
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(Text.class);
        
        job2.setMapperClass(CoocurrenceMapper.class);
        job2.setReducerClass(CoocurrenceReducer.class);
        job2.setJarByClass(RecommendSystem.class);
        
        job2.setInputFormatClass(TextInputFormat.class);
        job2.setOutputFormatClass(TextOutputFormat.class);
        
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);
        job2.setMapOutputKeyClass(Text.class);
        job2.setMapOutputValueClass(IntWritable.class);
        
        job3.setMapperClass(DenominatorMapper.class);
        job3.setReducerClass(DenominatorReducer.class);
        job3.setJarByClass(RecommendSystem.class);
        
        job3.setOutputKeyClass(Text.class);
        job3.setOutputValueClass(IntWritable.class);
        
        job4.setMapperClass(ColumnAsKeyCoocurrenceMapper.class);
        job4.setReducerClass(ColumnAsKeyCoocurrenceReducer.class);
        job4.setJarByClass(RecommendSystem.class);
        
        job4.setOutputKeyClass(Text.class);
        job4.setOutputValueClass(Text.class);
        
        job5.setMapperClass(UserMovie_ScoreMapper.class);
        job5.setReducerClass(UserMovie_ScoreReducer.class);
        job5.setJarByClass(RecommendSystem.class);
        
        job5.setOutputKeyClass(Text.class);
        job5.setOutputValueClass(DoubleWritable.class);
        
        job6.setMapperClass(User_MovieScoreMapper.class);
        job6.setReducerClass(User_MovieScoreReducer.class);
        job6.setJarByClass(RecommendSystem.class);
        
        job6.setOutputKeyClass(Text.class);
        job6.setOutputValueClass(Text.class);
        
        TextInputFormat.setInputPaths(job1, new Path(args[0]));
        TextOutputFormat.setOutputPath(job1, new Path(args[1]));
        
        TextInputFormat.setInputPaths(job2, new Path(args[1]));
        TextOutputFormat.setOutputPath(job2, new Path(args[2]));
        
        FileInputFormat.setInputPaths(job3, new Path(args[2]));
        FileOutputFormat.setOutputPath(job3, new Path(args[3]));
        
        FileInputFormat.setInputPaths(job4, new Path(args[2]));
        FileOutputFormat.setOutputPath(job4, new Path(args[4]));
        
        FileInputFormat.setInputPaths(job5, new Path(args[0]));
        FileOutputFormat.setOutputPath(job5, new Path(args[5]));
        
        FileInputFormat.setInputPaths(job6, new Path(args[5]));
        FileOutputFormat.setOutputPath(job6, new Path(args[6]));
        
        boolean success = job1.waitForCompletion(true) && 
        		job2.waitForCompletion(true) && 
        		job3.waitForCompletion(true) &&
        		job4.waitForCompletion(true) &&
        		job5.waitForCompletion(true) &&
        		job6.waitForCompletion(true);
        
        System.exit(success ? 0 : 1);
    }
    
}
