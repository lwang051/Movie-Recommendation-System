package org.lingbo.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class User_MovieScoreReducer extends
		Reducer<Text, Text, Text, Text> {
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) 
			throws IOException, InterruptedException {
		String movie_ID = "none";
		Double max_score = (double) 0;
		for(Text t : values) {
			String[] array = t.toString().split(":");
			Double d = Double.valueOf(array[1].trim());
			if(d > max_score) {  							// TODO, if watched, continue
				max_score = d;
				movie_ID = array[0].trim();
			}
		}
		context.write(key, new Text(movie_ID + ":" + max_score));
	}
}
