package io.practical.p0004;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class P0004 {

	public static void main(String[] args) {

//		int count = 0;
//
//		List<Future<Integer>> list = new ArrayList<Future<Integer>>();
//
//		Callable<Integer> counter = new CounterCallable(count);
//		ExecutorService executorService = Executors.newFixedThreadPool(4);
//
//		for (int i = 0; i < 1_000_000; i++) {
//			// execute tasks
//			Future<Integer> future = executorService.submit(counter);
//			list.add(future);
//		}
//		//print return values;
//		for (Future<Integer> fut : list) {
//			try {
//				System.out.println(fut.get());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			}
//
//		}
//		executorService.shutdown();
//	}
		int count = 0;
		
		CounterSimple counter = new CounterSimple(count);
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < 1_000_000; i++) {
			// execute tasks
			executorService.submit(counter);
		}
		executorService.shutdown();
	}
}