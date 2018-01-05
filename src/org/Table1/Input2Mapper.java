package org.Table1;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class Input2Mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text>
{
	private String artistID, location, fileTag = "FILE2~";

	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException
	{
		// taking one line/record at a time and parsing them into key value
		// pairs
		String line = value.toString();
		String splitarray[] = line.split("<SEP>");
		artistID = splitarray[0].trim(); // track_id

		if (line.endsWith("<SEP>") || line.endsWith("<SEP> "))
		{
			// this is because one of the entry has no location and another
			// entry has no location
			// too but end with space " " after <SEP>.
			// the if statement here to keep the files as it is without changing
			// it

		} else
		{
			location = splitarray[4].trim(); // artist name (I think)
			// sending the key value pair out of mapper
			output.collect(new Text(artistID), new Text(fileTag + location));
		}

		// System.out.println(artistID + ":" + location);

	}
}