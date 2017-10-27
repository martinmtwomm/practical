package io.practical.p0004;

public class Foo implements Runnable {
	private  int counter;

	@Override
	public void run() {
		increment();

	}

	private void increment() {
		counter = counter + 1;

	}

}