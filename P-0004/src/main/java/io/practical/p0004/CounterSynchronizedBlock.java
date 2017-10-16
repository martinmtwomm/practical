package io.practical.p0004;

public class CounterSynchronizedBlock implements Runnable {

	private int count;

	public CounterSynchronizedBlock(int count) {
		this.count = count;
	}

	void increment() {
		synchronized (this) {
			this.count++;
		}
	}

	@Override
	public void run() {
		increment();
	}

}
