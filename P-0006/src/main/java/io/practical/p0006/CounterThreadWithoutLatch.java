package io.practical.p0006;

public class CounterThreadWithoutLatch implements Runnable {

	private Counter counter;

	public CounterThreadWithoutLatch(Counter counter) {
		this.counter = counter;
	}

	public CounterThreadWithoutLatch(UnsafeCounter counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		counter.inc();
	}

}
