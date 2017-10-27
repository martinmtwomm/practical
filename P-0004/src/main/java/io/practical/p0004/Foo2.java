package io.practical.p0004;

public class Foo2 implements Runnable {
	private volatile int counter;

	@Override
	public void run() {
		increment();

	}

	private synchronized void increment() {
		synchronized (this) {
			counter = counter + 1;
		}
		

	}

}