# JavaMRReduceSideJoin
Java MapReduce code to perform reduce side join operation in MapReduce.

## Getting Started

Purpose : Calculate total sales by state given two csv input files - customers and sales.

Input files Schema:

customers : customer_id,name,street,city,state,zip

sales : timestamp,customer_id,sales_price

Output file Schema:

output_file : state,total_sales

### Prerequisites
Install below softwares: (Installation steps and links are given below for mac machine)

```
Java - 1.8 (https://java.com/en/download/help/mac_install.xml)
Hadoop - 2.8.2 (https://dtflaneur.wordpress.com/2015/10/02/installing-hadoop-on-mac-osx-el-capitan/)
```

### Installing

Download/Clone this repo.

1. For running in local mode in hadoop and testing within eclipse:
  Provide input files and output paths under arguments section inside run configuration.

```
That is below mentioned paths will need to be copied under arguments section inside run configuration: 
InputOutputDir/customers InputOutputDir/sales InputOutputDir/opFile
```

2. For running in cluster mode in hadoop and running from command line:
   Please run below commands from the command line(Please replace 'username' with your name):
  
   sh /usr/local/Cellar/hadoop/2.8.2/sbin/start-all.sh
  
   hadoop fs -mkdir /usr/username/input
  
   hadoop fs -mkdir /usr/username/output
   
   cd InputOutput
   
   hadoop fs -put customers /usr/username/input
  
   hadoop fs -put sales /usr/username/input
  
   sh /usr/local/Cellar/hadoop/2.8.2/sbin/stop-all.sh
  
   cd bin
   sh runMr.sh

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
