package io.practical.p0004;

public class Foo1 implements Runnable {
	private  int counter;

	@Override
	public void run() {
		increment();

	}

	private synchronized void increment() {
		counter = counter + 1;

	}

}