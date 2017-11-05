package io.practical.p0002;
import java.util.concurrent.RecursiveTask;

public class IncrementorTask extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 1L;
	private int value;
	private String name;

	public IncrementorTask(String name) {
		this.name = name;
		this.value = 0;
	}

	private void increment() {
		value++;
	}

	@Override
	protected Integer compute() {
		while (value < 10000) {
			increment();
		}
		System.out.println("finished" + this.name);
		return value;
	}

}
