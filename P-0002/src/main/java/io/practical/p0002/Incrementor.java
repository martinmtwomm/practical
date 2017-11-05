package io.practical.p0002;

public class Incrementor implements Runnable {

	private int value;
	private String name;

	public Incrementor(String name) {
		this.name = name;
		this.value = 0;
		run();
	}

	private void increment() {
		value++;
	}

	@Override
	public void run() {
		while (value < 1000000000) {
			increment();
		}
		System.out.println("finished" + this.name + " " + value);

	}

}
