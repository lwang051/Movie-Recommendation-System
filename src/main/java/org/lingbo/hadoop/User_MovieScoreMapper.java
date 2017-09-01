package org.lingbo.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class User_MovieScoreMapper extends Mapper<Object, Text, Text, Text> {
	@Override
	protected void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] array = value.toString().split("\\t");
		String[] array1 = array[0].split(":");
		context.write(new Text(array1[0].trim()), new Text(array1[1].trim() + ":" + array[1].trim()));
	}
}
