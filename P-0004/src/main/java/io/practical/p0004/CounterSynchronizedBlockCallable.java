package io.practical.p0004;

import java.util.concurrent.Callable;

public class CounterSynchronizedBlockCallable implements Callable<Integer> {

	private int count;

	public CounterSynchronizedBlockCallable(int count) {
		this.count = count;
	}

	private void increment() {
		synchronized (this) {
			count++;
		}

	}

	@Override
	public Integer call() throws Exception {
		increment();
		return count;
	}

}
