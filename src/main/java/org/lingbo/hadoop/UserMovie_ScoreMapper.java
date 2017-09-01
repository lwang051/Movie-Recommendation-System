package org.lingbo.hadoop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UserMovie_ScoreMapper extends
		Mapper<Object, Text, Text, DoubleWritable> {
	
	Map<String, Integer> denominator = new HashMap<String, Integer>();
	Map<String, String> columnAsKeyCoocurrence = new HashMap<String, String>();
	Map<String, Set<String>> watchList = new HashMap<String, Set<String>>();
	
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		FileSystem fs = FileSystem.get(conf);
		BufferedReader br1 = new BufferedReader(
				new InputStreamReader(fs.open(new Path(conf.get("denominator")))));
		BufferedReader br2 = new BufferedReader(
				new InputStreamReader(fs.open(new Path(conf.get("columnAsKeyCoocurrence")))));
		BufferedReader br3 = new BufferedReader(
				new InputStreamReader(fs.open(new Path(conf.get("output1")))));
		String line = br1.readLine();
		while(line != null) {
			String[] array = line.split("\\t");
			denominator.put(array[0].trim(), Integer.parseInt(array[1].trim()));
			line = br1.readLine();
		}
		line = br2.readLine();
		while(line != null) {
			String[] array = line.split("\\t");
			columnAsKeyCoocurrence.put(array[0].trim(), array[1].trim());
			line = br2.readLine();
		}
		line = br3.readLine();
		while(line != null) {
			String[] array = line.split("\\t");
			String user_ID = array[0].trim();
			String[] array2 = array[1].split(",");
			Set<String> set = new HashSet<String>();
			for(String movie_ID : array2) {
				set.add(movie_ID);
			}
			watchList.put(user_ID, set);
			line = br3.readLine();
		}
		br1.close();
		br2.close();
		br3.close();
	}
	
	@Override
	protected void map(Object key, Text value, Context context) 
			throws IOException, InterruptedException {
		String[] array = value.toString().split(",");
		String user_ID = array[0].trim();
		String[] array1 = columnAsKeyCoocurrence.get(array[1].trim()).split(",");
		for(String s : array1) {
			String[] array2 = s.trim().split(":");
			String movie_ID = array2[0].trim();
			if(!watchList.get(user_ID).contains(movie_ID)) {
				Double score = Double.parseDouble(array2[1].trim()) / denominator.get(movie_ID);
				score *= Double.parseDouble(array[2].trim());
				context.write(new Text(user_ID + ":" + movie_ID), new DoubleWritable(score));
			}
		}
	}
}
