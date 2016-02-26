package com.bitwiseglobal.assembly;

/**
 * Created by balajeev on 2/19/2016.
 */
import java.util.List;

import org.junit.Test;

import cascading.pipe.Pipe;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;

import com.hotels.plunger.Bucket;
import com.hotels.plunger.Data;
import com.hotels.plunger.DataBuilder;
import com.hotels.plunger.Plunger;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class SomeSubAssemblyTest {


    @Test

    public void test ()
    {
        Plunger plunger=new Plunger();

        Data corpus = new DataBuilder(new Fields("credits","bonus","names"))
                .addTuple(10,50,"Ravi")
                .addTuple(10,30,"Amit")
                .addTuple(05,12,"Vishal")
                .build();

        Pipe pipe = plunger.newNamedPipe("in", corpus);

        Pipe assemblyToTest = new someSubAssem(pipe);

        Bucket bucket = plunger.newBucket(new Fields("credits","bonus","names"), assemblyToTest);

        List<Tuple> actual = bucket.result().asTupleList();

        assertThat(actual.get(0), is(new Tuple(05,12,"Vishal")));
        assertThat(actual.get(1), is(new Tuple(10,30,"Amit")));



    }




}
