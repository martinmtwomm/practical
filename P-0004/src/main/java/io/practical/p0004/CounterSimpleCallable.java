package io.practical.p0004;

import java.util.concurrent.Callable;

public class CounterSimpleCallable implements Callable<Integer> {

	private int count;

	public CounterSimpleCallable(int count) {
		this.count = count;
	}
	
	
	private void increment() {
		count++;
	}

	@Override
	public Integer call() throws Exception {
		increment();
		return count;
	}

}
