package io.practical.p0006;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class P0006 {

	private static final int ONE_THOUSAND_K = 100_000;

	public P0006() {

	}

//	@Benchmark
//	@OutputTimeUnit(TimeUnit.SECONDS)
//	public void testMethod00() throws Exception {
//		CountDownLatch latch = new CountDownLatch(ONE_THOUSAND_K);
//		UnsafeCounter counter = new UnsafeCounter();
//
//		for (int i = 0; i < ONE_THOUSAND_K; i++) {
//			ForkJoinPool.commonPool().execute(new CounterThread(counter, latch));
//		}
//
//	}
//	@Benchmark
//	@OutputTimeUnit(TimeUnit.SECONDS)
//	public void testMethod0() throws Exception {
//		CountDownLatch latch = new CountDownLatch(ONE_THOUSAND_K);
//		UnsafeCounter counter = new UnsafeCounter();
//		ForkJoinPool pool = new ForkJoinPool(2);
//		for (int i = 0; i < ONE_THOUSAND_K; i++) {
//			pool.execute(new CounterThread(counter, latch));
//		}
//
//	}
//	
//	@Benchmark
//	@OutputTimeUnit(TimeUnit.SECONDS)
//	public void testMethod1() throws Exception {
//		CountDownLatch latch = new CountDownLatch(ONE_THOUSAND_K);
//		UnsafeCounter counter = new UnsafeCounter();
//		ForkJoinPool pool = new ForkJoinPool(4);
//		for (int i = 0; i < ONE_THOUSAND_K; i++) {
//			pool.execute(new CounterThread(counter, latch));
//		}
//
//	}
//	@Benchmark
//	@OutputTimeUnit(TimeUnit.SECONDS)
//	public void testMethod2() throws Exception {
//		CountDownLatch latch = new CountDownLatch(ONE_THOUSAND_K);
//		UnsafeCounter counter = new UnsafeCounter();
//		ForkJoinPool pool = new ForkJoinPool(8);
//		for (int i = 0; i < ONE_THOUSAND_K; i++) {
//			pool.execute(new CounterThread(counter, latch));
//		}
//
//	}
//	@Benchmark
//	@OutputTimeUnit(TimeUnit.SECONDS)
//	public void testMethod3() throws Exception {
//		CountDownLatch latch = new CountDownLatch(ONE_THOUSAND_K);
//		UnsafeCounter counter = new UnsafeCounter();
//		ForkJoinPool pool = new ForkJoinPool(16);
//		for (int i = 0; i < ONE_THOUSAND_K; i++) {
//			pool.execute(new CounterThread(counter, latch));
//		}
//
//	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void testMethod0() throws Exception {
		CountDownLatch latch = new CountDownLatch(ONE_THOUSAND_K);
		UnsafeCounter counter = new UnsafeCounter();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for (int i = 0; i < ONE_THOUSAND_K; i++) {
			executor.execute(new CounterThread(counter, latch));
		}
		executor.shutdown();

		try {
			latch.await();
			executor.shutdown();
			// System.out.println(counter.getValue());
			counter.reset();
			// System.out.println(counter.getValue());

		} catch (InterruptedException e) {
			throw new RuntimeException("interrupted");
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void testMethod1() throws Exception {
		CountDownLatch latch = new CountDownLatch(ONE_THOUSAND_K);
		UnsafeCounter counter = new UnsafeCounter();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < ONE_THOUSAND_K; i++) {
			executor.execute(new CounterThread(counter, latch));
		}

		try {
			latch.await();
			executor.shutdown();
			counter.reset();
			// System.out.println(counter.getValue());
		} catch (InterruptedException e) {
			throw new RuntimeException("interrupted");
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void testMethod2() throws Exception {
		CountDownLatch latch = new CountDownLatch(ONE_THOUSAND_K);
		UnsafeCounter counter = new UnsafeCounter();
		ExecutorService executor = Executors.newFixedThreadPool(8);
		for (int i = 0; i < ONE_THOUSAND_K; i++) {
			executor.execute(new CounterThread(counter, latch));
		}
		try {
			latch.await();
			executor.shutdown();
			counter.reset();
			// System.out.println(counter.getValue());
		} catch (InterruptedException e) {
			throw new RuntimeException("interrupted");
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void testMethod3() throws Exception {
		CountDownLatch latch = new CountDownLatch(ONE_THOUSAND_K);
		UnsafeCounter counter = new UnsafeCounter();
		ExecutorService executor = Executors.newFixedThreadPool(16);
		for (int i = 0; i < ONE_THOUSAND_K; i++) {
			executor.execute(new CounterThread(counter, latch));
		}
		try {
			latch.await();
			executor.shutdown();
			counter.reset();
			// System.out.println(counter.getValue());
		} catch (InterruptedException e) {
			throw new RuntimeException("interrupted");
		}
	}

}
