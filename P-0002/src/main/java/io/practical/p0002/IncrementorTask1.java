package io.practical.p0002;
import java.util.concurrent.RecursiveTask;

public class IncrementorTask1 extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 1L;
	private int value;
	private boolean fork;
	private String name;

	public IncrementorTask1(String name, boolean fork) {
		this.name = name;
		this.value = 0;
		this.fork = fork;
	}

	private void increment() {
		value++;
	}

	@Override
	protected Integer compute() {
		if (fork) {
			IncrementorTask1 task1 = new IncrementorTask1("task1", false);
			IncrementorTask1 task2 = new IncrementorTask1("task2", false);
			IncrementorTask1 task3 = new IncrementorTask1("task3", false);
			task1.fork();
			task2.fork();
			task3.fork();
			fork = false;
			return compute() + task1.join() + task2.join() + task3.join();
		} else {
			System.out.println("task name: " + name);
			while (value < 10000) {
				increment();
			}
			return value;
		}
	}

}
