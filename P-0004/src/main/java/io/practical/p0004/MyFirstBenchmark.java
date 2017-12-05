package io.practical.p0004;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import counter.thread.CounterThread;
import counter.thread.CounterThreadMethodSynchronized;
import io.practical.p0004.counter.impl.Counter0Simple;
import io.practical.p0004.counter.impl.Counter1SynchronizedBlock;
import io.practical.p0004.counter.impl.Counter2MethodSynchronized;
import io.practical.p0004.counter.impl.Counter3ReentrantLock;
import io.practical.p0004.counter.impl.Counter4Atomic;

@State(Scope.Thread)
public class MyFirstBenchmark {
	

	private static final int INCREMENTS = 1_000_000;
	
	
//	Benchmark                                      Mode  Cnt    Score   Error  Units
//	MyFirstBenchmark.atomicCounterMT               thrpt   20    4.401 ± 0.075  ops/s
//	MyFirstBenchmark.atomicCounter                thrpt   20  215.627 ± 2.163  ops/s
//	MyFirstBenchmark.reentrantLockCounter         thrpt   20   69.315 ± 1.066  ops/s
//	MyFirstBenchmark.reentrantLockCounterMT       thrpt   20    3.761 ± 0.130  ops/s
//	MyFirstBenchmark.simpleCounter                thrpt   20  416.807 ± 4.306  ops/s
//	MyFirstBenchmark.simpleCounterMT              thrpt   20    4.262 ± 0.137  ops/s
//	MyFirstBenchmark.synchonizedBlockCounterMT    thrpt   20    3.997 ± 0.177  ops/s
//	MyFirstBenchmark.synchronizedBlockCounter     thrpt   20  416.298 ± 3.503  ops/s
//	MyFirstBenchmark.synchronizedMethodCounter    thrpt   20  410.813 ± 9.383  ops/s
//	MyFirstBenchmark.synchronizedMethodCounterMT  thrpt   20    4.129 ± 0.138  ops/s
//	Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 436.586 sec

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void simpleCounter() {
		Counter0Simple counter = new Counter0Simple();
		for (int i = 0; i < INCREMENTS; i++) {
			counter.increment();
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void synchronizedBlockCounter() {

		Counter1SynchronizedBlock counter = new Counter1SynchronizedBlock();

		for (int i = 0; i < INCREMENTS; i++) {
			counter.increment();
		}

	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void synchronizedMethodCounter() {

		Counter2MethodSynchronized counter = new Counter2MethodSynchronized();

		for (int i = 0; i < INCREMENTS; i++) {
			counter.increment();
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void reentrantLockCounter() {
		Counter3ReentrantLock counter = new Counter3ReentrantLock();

		for (int i = 0; i < INCREMENTS; i++) {
			counter.increment();
		}

	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void atomicCounter() {
		Counter4Atomic counter = new Counter4Atomic();
		for (int i = 0; i < INCREMENTS; i++) {
			counter.increment();
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void simpleCounterMT() {

		CountDownLatch latch = new CountDownLatch(INCREMENTS);
		Counter0Simple counter = new Counter0Simple();
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < INCREMENTS; i++) {

			executorService.execute(new CounterThread(counter, latch));
		}

		try {
			latch.await();
			executorService.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void synchonizedBlockCounterMT() {

		CountDownLatch latch = new CountDownLatch(INCREMENTS);
		Counter1SynchronizedBlock counter = new Counter1SynchronizedBlock();
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < INCREMENTS; i++) {
			executorService.execute(new CounterThread(counter, latch));
		}

		try {
			latch.await();
			executorService.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void synchronizedMethodCounterMT() {

		CountDownLatch latch = new CountDownLatch(INCREMENTS);
		Counter2MethodSynchronized counter = new Counter2MethodSynchronized();
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < INCREMENTS; i++) {
			executorService.execute(new CounterThreadMethodSynchronized(counter, latch));
		}

		try {
			latch.await();
			executorService.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void reentrantLockCounterMT() {

		CountDownLatch latch = new CountDownLatch(INCREMENTS);
		Counter3ReentrantLock counter = new Counter3ReentrantLock();
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < INCREMENTS; i++) {
			executorService.execute(new CounterThread(counter, latch));
		}

		try {
			latch.await();
			executorService.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void atomicCounterMT() {

		CountDownLatch latch = new CountDownLatch(INCREMENTS);
		Counter4Atomic counter = new Counter4Atomic();
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < INCREMENTS; i++) {
			executorService.execute(new CounterThread(counter, latch));
		}

		try {
			latch.await();
			executorService.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
