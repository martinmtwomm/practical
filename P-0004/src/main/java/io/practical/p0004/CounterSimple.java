package io.practical.p0004;

public class CounterSimple implements Runnable {

	private int count;

	public CounterSimple(int count) {
		this.count = count;
	}

	void increment() {
		this.count++;
	}

	@Override
	public void run() {
		increment();
	}

}
