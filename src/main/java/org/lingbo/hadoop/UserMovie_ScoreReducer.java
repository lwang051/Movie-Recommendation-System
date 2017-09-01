package org.lingbo.hadoop;

import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class UserMovie_ScoreReducer extends
		Reducer<Text, DoubleWritable, Text, DoubleWritable> {
	@Override
	protected void reduce(Text key, Iterable<DoubleWritable> values, Context context)
			throws IOException, InterruptedException {
		Double sum = (double) 0;
		for(DoubleWritable dw : values) {
			sum += dw.get();
		}
		DecimalFormat df = new DecimalFormat("#.00");
		sum = Double.valueOf(df.format(sum));
		context.write(key, new DoubleWritable(sum));
	}
}
