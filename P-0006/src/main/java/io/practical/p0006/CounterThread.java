package io.practical.p0006;

import java.util.concurrent.CountDownLatch;

public class CounterThread implements Runnable {

	private Counter counter;
	private CountDownLatch latch;

	public CounterThread(Counter counter, CountDownLatch latch) {
		this.counter = counter;
		this.latch = latch;
	}

	public CounterThread(UnsafeCounter counter, CountDownLatch latch) {
		this.counter = counter;
		this.latch = latch;
	}

	@Override
	public void run() {
		counter.inc();
		latch.countDown();
	}

}
