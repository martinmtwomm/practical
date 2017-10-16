package io.practical.p0004;

public class CounterSynchronized implements Runnable {

	private int count;

	public CounterSynchronized(int count) {
		this.count = count;
	}



	synchronized void increment() {
		this.count++;
	}

	@Override
	public void run() {
		increment();
	}

}
