package com.nbisht.mr.reducesidejoin;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author nbisht7. 
 * Purpose - Final reducer to calculate aggregate sales price for each state.
 * Input Schema - state, salesPrice
 * Output Schema - state, totalSalesPrice
*/

public class SalesAggregatorReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
	public void reduce(Text key, Iterable<DoubleWritable> values, Context context)
			throws IOException, InterruptedException {
		double totalSalesPrice = 0.0;
		for (DoubleWritable val : values) {
			totalSalesPrice = totalSalesPrice + Double.parseDouble(val.toString());
		}
		DoubleWritable totalSales = new DoubleWritable(totalSalesPrice);

		// Validating the data before writing
		if (!(key.toString().isEmpty()) && !(totalSales == null))
			context.write(new Text(key), totalSales);
	}
}
