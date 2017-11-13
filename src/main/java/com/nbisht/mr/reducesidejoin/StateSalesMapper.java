package com.nbisht.mr.reducesidejoin;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author nbisht7. 
 * Purpose - States Mapper to fetch the data from intermediate directory in hadoop. 
 * Input Schema - state, salesPrice
 * Output Schema - state, salesPrice
*/

public class StateSalesMapper extends Mapper<Object, Text, Text, DoubleWritable> {

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String itermediateData = value.toString();
		String statesData[] = itermediateData.split("\t");

		Text stateName = new Text(statesData[0]);
		DoubleWritable salesPrice = new DoubleWritable(Double.parseDouble(statesData[1]));

		// Validating the data before writing
		if (!(stateName.toString().isEmpty()) && !(salesPrice == null))
			context.write(stateName, salesPrice);
	}
}