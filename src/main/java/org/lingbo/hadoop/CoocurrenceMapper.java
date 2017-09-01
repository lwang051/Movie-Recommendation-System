package org.lingbo.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CoocurrenceMapper extends Mapper<Object, Text, Text, IntWritable> {
	@Override
	protected void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] array = (value.toString().split("\\t"))[1].split(",");
		IntWritable one = new IntWritable(1);
		for(int i = 0; i < array.length; i++) {
			for(int j = i; j < array.length; j++) {
				context.write(new Text(array[i].trim() + ":" + array[j].trim()), one);
			}
		}
	}
}
