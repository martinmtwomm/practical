package io.practical.p0004;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import counter.thread.CounterThread;
import counter.thread.CounterThreadMethodSynchronized;
import io.practical.p0004.counter.impl.Counter0Simple;
import io.practical.p0004.counter.impl.Counter1SynchronizedBlock;
import io.practical.p0004.counter.impl.Counter2MethodSynchronized;
import io.practical.p0004.counter.impl.Counter3ReentrantLock;
import io.practical.p0004.counter.impl.Counter4Atomic;

public class P0004 {
	private static final int INCREMENTS = 1_000_000;

	public P0004() {
	}

	public void testMethod00() {

		Counter0Simple counter = new Counter0Simple();
		for (int i = 0; i < INCREMENTS; i++) {
			counter.increment();
		}
		System.out.println(counter);
	}

	public void testMethod01() {

		Counter1SynchronizedBlock counter = new Counter1SynchronizedBlock();

		for (int i = 0; i < INCREMENTS; i++) {
			counter.increment();
		}
		System.out.println(counter);

	}

	public void testMethod02() {

		Counter2MethodSynchronized counter = new Counter2MethodSynchronized();

		for (int i = 0; i < INCREMENTS; i++) {
			counter.increment();
		}
		System.out.println(counter);
	}

	public void testMethod03() {
		Counter3ReentrantLock counter = new Counter3ReentrantLock();

		for (int i = 0; i < INCREMENTS; i++) {
			counter.increment();
		}
		System.out.println(counter);

	}

	public void testMethod04() {
		Counter4Atomic counter = new Counter4Atomic();
		for (int i = 0; i < INCREMENTS; i++) {
			counter.increment();
		}
		System.out.println(counter);
	}

	public void testMethod0() {

		CountDownLatch latch = new CountDownLatch(INCREMENTS);
		Counter0Simple counter = new Counter0Simple();
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < INCREMENTS; i++) {

			executorService.execute(new CounterThread(counter, latch));
		}

		try {
			latch.await();
			executorService.shutdown();
			System.out.println(counter);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void testMethod1() {

		CountDownLatch latch = new CountDownLatch(INCREMENTS);
		Counter1SynchronizedBlock counter = new Counter1SynchronizedBlock();
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < INCREMENTS; i++) {
			executorService.execute(new CounterThread(counter, latch));
		}

		try {
			latch.await();
			executorService.shutdown();
			System.out.println(counter);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void testMethod2() {

		CountDownLatch latch = new CountDownLatch(INCREMENTS);
		Counter2MethodSynchronized counter = new Counter2MethodSynchronized();
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < INCREMENTS; i++) {
			executorService.execute(new CounterThreadMethodSynchronized(counter, latch));
		}

		try {
			latch.await();
			executorService.shutdown();
			System.out.println(counter);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void testMethod3() {

		CountDownLatch latch = new CountDownLatch(INCREMENTS);
		Counter3ReentrantLock counter = new Counter3ReentrantLock();
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < INCREMENTS; i++) {
			executorService.execute(new CounterThread(counter, latch));
		}

		try {
			latch.await();
			executorService.shutdown();
			System.out.println(counter);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void testMethod4() {

		CountDownLatch latch = new CountDownLatch(INCREMENTS);
		Counter4Atomic counter = new Counter4Atomic();
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 0; i < INCREMENTS; i++) {
			executorService.execute(new CounterThread(counter, latch));
		}

		try {
			latch.await();
			System.out.println(counter);
			executorService.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		P0004 p = new P0004();

		p.testMethod00();
		p.testMethod01();
		p.testMethod02();
		p.testMethod03();
		p.testMethod04();
		p.testMethod00();
		p.testMethod01();
		p.testMethod02();
		p.testMethod03();
		p.testMethod04();

	}
}