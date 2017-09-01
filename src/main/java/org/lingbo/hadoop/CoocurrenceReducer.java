package org.lingbo.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CoocurrenceReducer extends
		Reducer<Text, IntWritable, Text, Text> {
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		for(IntWritable i : values) {
			sum += i.get();
		}
		String[] array = key.toString().split(":");
		
		context.write(new Text(array[0]), new Text(array[1] + ":" + sum));
	}
}
