//A template for a regular wordcount class is used to count
// a specific field and produce the output.
//Because the keys is "location" , the output 
// will be ordered accordingly.
//and the values (fields) where splitted using 
//the "split" function according to the separator "<SEP>".
//One mapper is used with the same technique of 
//separation. In the reducer a simple counting was used.

package org.Table2;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Table2
{

	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable>
	{

		private final static IntWritable one = new IntWritable(1);
		private Text location = new Text();

		public void map(Object key, Text value, Context context) throws IOException, InterruptedException
		{

			String data = value.toString();

			String field[] = data.split("<SEP>");

			location.set(field[3]);

			context.write(location, one);

		}
	}

	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable>
	{
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException
		{

			// basic counting.
			int sum = 0;
			for (IntWritable val : values)
			{
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}

	public static void main(String[] args) throws Exception
	{
		// JobConf conf = new JobConf(getConf(),
		// org.Table1.ConfigurationClass.class);

		Configuration conf = new Configuration();
		conf.set(TextOutputFormat.SEPERATOR, "<SEP>");
		Job job = Job.getInstance(conf, "word count");
		job.setJarByClass(Table2.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0] + "/Table1.txt"));
		// FileInputFormat.addInputPath(job, new Path(args[0] +
		// "/t3_artist_location.txt"));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}