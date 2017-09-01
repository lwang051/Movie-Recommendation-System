package org.lingbo.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class UserMoviesReducer extends Reducer<Text, Text, Text, Text> {
	
	// protected
	protected void reduce(Text key, Iterable<Text> values, Context context) 
			throws IOException,InterruptedException {
		StringBuilder sb = new StringBuilder();
		for(Text t : values) {
			sb.append("," + t.toString());
		}
		String str = sb.toString().replaceFirst(",", "");
		context.write(key, new Text(str));
	}
	
}
