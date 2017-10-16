package io.practical.p0004;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;

public class MyFirstBenchmark {

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void testMethodSimple() {
		int count = 0;

		CounterSimple counter = new CounterSimple(count);
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < 1_000_000; i++) {
			// execute tasks
			executorService.execute(counter);
		}
		executorService.shutdown();
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void testMethodSyncMethod() {
		int count = 0;

		CounterSynchronized counter = new CounterSynchronized(count);
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < 1_000_000; i++) {
			// execute tasks
			executorService.execute(counter);
		}
		executorService.shutdown();
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void testMethodSyncBlock() {
		int count = 0;

		CounterSynchronizedBlock counter = new CounterSynchronizedBlock(count);
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < 1_000_000; i++) {
			// execute tasks
			executorService.execute(counter);
		}
		executorService.shutdown();
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void testMethodReentrantLock() {

		int count = 0;
		CounterReentrantLock counter = new CounterReentrantLock(count);
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < 1_000_000; i++) {
			// execute tasks
			executorService.execute(counter);
		}

		executorService.shutdown();
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void testMethodSimpleCallable() {
		int count = 0;

		List<Future<Integer>> list = new ArrayList<Future<Integer>>();

		Callable<Integer> counter = new CounterSimpleCallable(count);
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < 1_000_000; i++) {
			// execute tasks
			Future<Integer> future = executorService.submit(counter);
			list.add(future);
		}
		// print return values;
		// for (Future<Integer> fut : list) {
		// try {
		// System.out.println(fut.get());
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// } catch (ExecutionException e) {
		// e.printStackTrace();
		// }
		//
		// }

		// try {
		// System.out.println(list.get(list.size()-1).get());
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// } catch (ExecutionException e) {
		// e.printStackTrace();
		// }
		// executorService.shutdown();
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void testMethodSynchronizedCallable() {
		int count = 0;

		List<Future<Integer>> list = new ArrayList<Future<Integer>>();

		Callable<Integer> counter = new CounterSynchronizedCallable(count);
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < 1_000_000; i++) {
			// execute tasks
			Future<Integer> future = executorService.submit(counter);
			list.add(future);
		}

		// try {
		// System.out.println(list.get(list.size()-1).get());
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// } catch (ExecutionException e) {
		// e.printStackTrace();
		// }
		executorService.shutdown();
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void testMethodSynchronizedBlockCallable() {
		int count = 0;

		List<Future<Integer>> list = new ArrayList<Future<Integer>>();

		Callable<Integer> counter = new CounterSynchronizedBlockCallable(count);
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < 1_000_000; i++) {
			// execute tasks
			Future<Integer> future = executorService.submit(counter);
			list.add(future);
		}
		// try {
		// System.out.println(list.get(list.size()-1).get());
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// } catch (ExecutionException e) {
		// e.printStackTrace();
		// }
		executorService.shutdown();
	}

}
