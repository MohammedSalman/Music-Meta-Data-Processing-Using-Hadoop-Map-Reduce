package org.Table1;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class Input1Mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text>
{
	// variables to process Consumer Details
	private String artist_id, track_id, year, fileTag = "FILE1~";

	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException
	{
		// taking one line/record at a time and parsing them into key value
		// pairs
		String line = value.toString();
		String splitarray[] = line.split("<SEP>");
		track_id = splitarray[0].trim(); // track_id
		artist_id = splitarray[2].trim(); // artist id
		year = splitarray[1].trim(); // year
		// sending the key value pair out of mapper
		output.collect(new Text(artist_id), new Text(fileTag + track_id + "<SEP>" + year));
	}
}
