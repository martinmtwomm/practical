package io.practical.p0004;

public class CounterSimple implements Runnable {

	private int count;

	public CounterSimple() {
		
	}

	void increment() {
		this.count++;
	}

	@Override
	public void run() {
		increment();
	}

}
