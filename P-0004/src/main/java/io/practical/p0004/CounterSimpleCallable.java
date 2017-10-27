package io.practical.p0004;

import java.util.concurrent.Callable;

public class CounterSimpleCallable implements Callable<Integer> {

	private int count=0;

	public CounterSimpleCallable() {
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
