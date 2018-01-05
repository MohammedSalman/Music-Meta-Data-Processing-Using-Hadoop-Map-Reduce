package org.Table0;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class ReducerClass extends MapReduceBase implements Reducer<Text, Text, Text, Text>
{

	private String year, artistName;

	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException
	{
		year = null;
		artistName = null; // important to set these to null for each calling
							// for reduce.
		while (values.hasNext())
		{
			String currValue = values.next().toString();
			String valueSplitted[] = currValue.split("~"); // Before "~" either
															// "FILE1" or
															// "FILE2".

			if (valueSplitted[0].equals("FILE2")) // the current line from file2
			{
				year = valueSplitted[1];
			} else if (valueSplitted[0].equals("FILE1")) // the current line
															// from file1
			{
				artistName = valueSplitted[1];
			}
		}
		if (year != null && artistName != null)
		{
			output.collect(new Text(key), new Text(year + "<SEP>" + artistName)); // produce
																					// the
																					// output
		}

	}

}
//
