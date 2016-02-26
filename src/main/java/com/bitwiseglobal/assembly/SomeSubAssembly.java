package com.bitwiseglobal.assembly;

/**
 * Created by balajeev on 2/19/2016.
 */

import java.util.Collections;
import java.util.Properties;

import cascading.flow.Flow;
import cascading.flow.FlowDef;
import cascading.flow.hadoop2.Hadoop2MR1FlowConnector;
import cascading.operation.Identity;
import cascading.operation.aggregator.First;
import cascading.operation.aggregator.Sum;
import cascading.pipe.Each;
import cascading.pipe.Every;
import cascading.pipe.GroupBy;
import cascading.pipe.Pipe;
import cascading.property.AppProps;
import cascading.scheme.Scheme;
import cascading.scheme.hadoop.TextDelimited;
import cascading.tap.SinkMode;
import cascading.tap.Tap;
import cascading.tap.hadoop.Hfs;
import cascading.tap.hadoop.Lfs;
import cascading.tuple.Fields;


public class SomeSubAssembly {

    public static void main(String args[])
    {
        Scheme inputPath=new TextDelimited(new Fields("credits","bonus","names"),"\t");
        Tap source=new Lfs(inputPath,"/home/edureka/balajee/names.txt");
        Scheme outputPath=new TextDelimited();
        Tap sink=new Hfs(outputPath,args[0],SinkMode.REPLACE);
        Pipe p1=new Pipe("p1");

        p1=new someSubAssem(p1);

        Properties properties=new Properties();
        AppProps.setApplicationJarClass(properties, SomeSubAssembly.class);
        FlowDef flowDef=FlowDef.flowDef().addSource(p1, source).addTailSink(p1 , sink);
        Flow flow=new Hadoop2MR1FlowConnector(properties).connect(flowDef);
        flow.complete();
    }


}
