package io.practical.p0004;

import java.util.concurrent.Callable;

public class CounterSynchronizedCallable implements Callable<Integer> {

	private int count;

	public CounterSynchronizedCallable(int count) {
		this.count = count;
	}

	private synchronized void increment() {
		count++;
	}

	@Override
	public Integer call() throws Exception {
		increment();
		return count;
	}

}
