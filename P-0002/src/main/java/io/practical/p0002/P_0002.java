package io.practical.p0002;
import java.util.concurrent.ForkJoinPool;

public class P_0002 {

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool(4);

		int result = 0;
		IncrementorTask task0 = new IncrementorTask("Task0");
		IncrementorTask task1 = new IncrementorTask("Task1");
		IncrementorTask task2 = new IncrementorTask("Task2");
		IncrementorTask task3 = new IncrementorTask("Task3");
		result += pool.invoke(task0);
		result += pool.invoke(task1);
		result += pool.invoke(task2);
		result += pool.invoke(task3);

		// for (int i = 0; i < 4; i++) {
		// pool.execute(new Incrementor("task"+i));
		//
		// }
		System.out.println("finshed: count → " + result);

		pool.shutdown();

	}
}
