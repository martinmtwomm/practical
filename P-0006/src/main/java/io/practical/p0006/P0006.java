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
	
//	Benchmark           Mode  Cnt   Score   Error  Units
//	P0006.testMethod0  thrpt   20  44.236 ± 0.437  ops/s    -> 2 Threads
//	P0006.testMethod1  thrpt   20  41.887 ± 0.606  ops/s	-> 4 Threads
//	P0006.testMethod2  thrpt   20  44.050 ± 0.416  ops/s	-> 8 Threads
//	P0006.testMethod3  thrpt   20  43.155 ± 0.348  ops/s	- 16 Threads
//	Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 165.216 sec

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void testMethod0() throws Exception {
		CountDownLatch latch = new CountDownLatch(ONE_THOUSAND_K);
		UnsafeCounter counter = new UnsafeCounter();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for (int i = 0; i < ONE_THOUSAND_K; i++) {
			executor.execute(new CounterThread(counter, latch));
		}
		

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
