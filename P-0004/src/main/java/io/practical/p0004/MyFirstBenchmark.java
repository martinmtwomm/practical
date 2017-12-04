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
	public void atomiCounterMT() {

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
