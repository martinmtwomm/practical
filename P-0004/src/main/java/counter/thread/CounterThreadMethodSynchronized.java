package counter.thread;

import java.util.concurrent.CountDownLatch;

import io.practical.p0004.counter.impl.Counter2MethodSynchronized;

public class CounterThreadMethodSynchronized implements Runnable {
	private Counter2MethodSynchronized counter;
	private CountDownLatch latch;

	public CounterThreadMethodSynchronized(Counter2MethodSynchronized counter, CountDownLatch latch) {
		this.counter = counter;
		this.latch = latch;
	}

	@Override
	public void run() {
		counter.increment();
		latch.countDown();
	}

}
