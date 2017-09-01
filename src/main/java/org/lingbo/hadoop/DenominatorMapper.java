package org.lingbo.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DenominatorMapper extends Mapper<Object, Text, Text, IntWritable> {
	@Override
	protected void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] array = value.toString().split("\\t");
		context.write(new Text(array[0].trim()), 
				new IntWritable(Integer.parseInt((array[1].split(":"))[1])));
	}
}
