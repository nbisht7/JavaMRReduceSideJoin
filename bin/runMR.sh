#!/bin/bash
#title           :runMR.sh
#description     :This script is to run the mapreduce join code.
#author          :nbisht7
#date            :11-12-2017
#version         :0.1    
#usage           :sh runMR.sh
#=========================================================================

#starting hadoop daemons
hstart=`exec /usr/local/Cellar/hadoop/2.8.2/sbin/start-all.sh`
res=`echo $?`
if [ $res == 0 ]; then
echo "Successfully started the hadoop daemons"
else
echo "Failed to start hadoop daemons"
exit
fi

#Defining hdfs input and output hadoop directories required by the mapreduce job
hdfs_input_dir="/usr/nbisht/input"
hdfs_output_dir="/usr/nbisht/output"

#Testing whether input files are present or not
hadoop fs -test -d $hdfs_input_dir
res=`echo $?`
if [ $res == 0 ]; then
echo "Input directory is present."
else
hadoop fs -mkdir -p /usr/nbisht/input
echo "Input directory created."
exit
fi

#removing the hadoop output directory if already exist
hadoop fs -test -d $hdfs_output_dir/opFile
res=`echo $?`
if [ $res == 0 ]; then
hadoop fs -rmr /usr/nbisht/output/opFile
echo "Output directory deleted."
else
echo "Output directed is not present."
exit
fi

#getting the current working directory
curr_dir=`pwd`
res=`echo $?`
if [ $res == 0 ]; then
echo "Got the current working directory = "$pwd" "
else
echo "Error in getting current working directory"
exit
fi

#Running the mapreduce code
echo "Mapreduce job started"
hadoop jar $curr_dir/reducesidejoin/target/reducesidejoin-0.0.1.jar com.nbisht.mr.reducesidejoin.CalculateTotalSales $hdfs_input_dir/customers $hdfs_input_dir/sales $hdfs_output_dir/opFile
res=`echo $?`
if [ $res == 0 ]; then
echo "Successfully completed the mapreduce job, output file directory is : "$hdfs_output_dir/opFile" "
else
echo "MapReduce job failed with errors"
exit
fi

#stopping hadoop daemons
hstop=`exec /usr/local/Cellar/hadoop/2.8.2/sbin/stop-all.sh`
res=`echo $?`
if [ $res == 0 ]; then
echo "Successfully stopped the hadoop daemons"
else
echo "Failed to stop hadoop daemons"
exit
fi
