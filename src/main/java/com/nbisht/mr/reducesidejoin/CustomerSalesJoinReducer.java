package com.nbisht.mr.reducesidejoin;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author nbisht7. 
 * Purpose - Joining customers and sales files based on CustomerID
 * Input Schema - Output from Customer Mapper : CustomerID, state and
 *                Output from Sales Mapper : CustomerID, salesPrice.
 * Output Schema - state, salesPrice without aggregation.
 */

public class CustomerSalesJoinReducer extends Reducer<IntWritable, Text, Text, DoubleWritable> {

	public void reduce(IntWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		String stateName = "";
		Double salesData = 0.0;

		for (Text val : values) {
			String data[] = val.toString().split("~");
			if (data[0].equals("customer")) {
				stateName = data[1];
			} else if (data[0].equals("sales")) {
				salesData = Double.parseDouble(data[1]);
			}
		}

		DoubleWritable salesPrice = new DoubleWritable(salesData);

		// Validating the data before writing
		if (!(stateName.toString().isEmpty()) && !(salesPrice == null))
			context.write(new Text(stateName), salesPrice);
	}
}