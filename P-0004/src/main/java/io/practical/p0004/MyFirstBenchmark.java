package io.practical.p0004;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openjdk.jmh.annotations.Benchmark;

public class MyFirstBenchmark {

	@Benchmark
	public void testMethod0() {

		ExecutorService executorService = Executors.newFixedThreadPool(4);

		Runnable counter = new Runnable() {
			int count = 0;

			private void increment() {
				count++;
			}

			@Override
			public void run() {
				increment();
			}
		};

		for (int i = 0; i < 1_000_000; i++) {
			executorService.submit(counter);
			// System.out.println(i);
		}

		// System.out.println("finished");

		executorService.shutdown();
	}

	@Benchmark
	public void testMethod1() {
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		Runnable counter = new Runnable() {
			int count = 0;

			private synchronized void increment() {
				count++;
			}

			@Override
			public void run() {
				increment();
			}
		};

		for (int i = 0; i < 1_000_000; i++) {
			executorService.submit(counter);
			// System.out.println(i);
		}

		// System.out.println("finished");

		executorService.shutdown();
	}

	@Benchmark
	public void testMethod2() {
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		Runnable counter = new Runnable() {
			int count = 0;

			private void increment() {
				synchronized (this) {
					count++;
				}

			}

			@Override
			public void run() {
				increment();
			}
		};

		for (int i = 0; i < 1_000_000; i++) {
			executorService.submit(counter);
			// System.out.println(i);
		}

		// System.out.println("finished");

		executorService.shutdown();
	}

	// @Benchmark
	// public void testMethod3() {
	//
	// }

}
