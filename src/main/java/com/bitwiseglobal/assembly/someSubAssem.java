package com.bitwiseglobal.assembly;

import cascading.operation.aggregator.First;
import cascading.pipe.Every;
import cascading.pipe.GroupBy;
import cascading.pipe.Pipe;
import cascading.pipe.SubAssembly;
import cascading.tuple.Fields;

/**
 * Created by balajeev on 2/19/2016.
 */
public class someSubAssem extends SubAssembly {


    public someSubAssem(Pipe p1) {


        setPrevious(p1);

        p1=new GroupBy(p1, Fields.NONE,new Fields("credits"),true);

        p1=new GroupBy(p1,new Fields("credits"),new Fields("names"));


        p1=new Every(p1,new First(5),Fields.RESULTS);

        setTails(p1);
    }
}
