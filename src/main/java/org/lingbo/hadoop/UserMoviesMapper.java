package org.lingbo.hadoop;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UserMoviesMapper extends Mapper<Object, Text, Text, Text> {
	
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] array = value.toString().split(",");
		String user_id = array[0].trim();
		String movie_id = array[1].trim();
		context.write(new Text(user_id), new Text(movie_id));
	}
	
}
