package io.practical.p0004;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openjdk.jmh.annotations.Benchmark;

import eu.toolchain.async.AsyncFramework;
import eu.toolchain.async.AsyncFuture;
import eu.toolchain.async.TinyAsync;

public class ManyThreads { 
    private static final int SIZE = 1000; 
 
    private static int THREAD_COUNT = Runtime.getRuntime().availableProcessors(); 
 
    @Benchmark 
    public void tiny() throws Exception { 
        final ExecutorService executor = Executors.newFixedThreadPool(4); 
//        final AsyncFramework async = TinyAsync.builder().executor(executor).build(); 
 
//        final List<AsyncFuture<Integer>> futures = new ArrayList<>(); 
 Callable<Integer> caa = new CounterSimpleCallable();
        for (int i = 0; i < 1000000; i++) { 
            caa.call();
        } 
 
 
//        for (int num : async.collect(futures).get()) 
//            sum += num; 
//        System.out.println(sum);
// 
//        if (sum != 1000000) 
//            throw new IllegalStateException("did not properly collect all values"); 
 
        executor.shutdown(); 
    } 
}
