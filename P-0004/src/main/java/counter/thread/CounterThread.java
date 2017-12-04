package counter.thread;

import java.util.concurrent.CountDownLatch;

import io.practical.p0004.counter.Counter;

public class CounterThread implements Runnable {

	Counter counter;
	CountDownLatch latch;

	public CounterThread(Counter counter, CountDownLatch latch) {
		this.counter = counter;
		this.latch = latch;
	}

	@Override
	public void run() {
		counter.increment();
		latch.countDown();
		
	}

}
