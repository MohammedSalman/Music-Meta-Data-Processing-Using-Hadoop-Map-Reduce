package org.Table0;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class Input2Mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text>
{
	private String track_id, artistID, artistName, fileTag = "FILE1~";

	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException
	{
		// taking one line/record at a time and parsing them into key value
		// pairs
		String line = value.toString();
		String splitarray[] = line.split("<SEP>");
		artistID = splitarray[0].trim(); // artistID
		track_id = splitarray[2].trim(); // track_id
		artistName = splitarray[3].trim(); // artist name
		// sending the key value pair out of mapper
		output.collect(new Text(track_id), new Text(fileTag + artistID + "<SEP>" + artistName));
	}
}