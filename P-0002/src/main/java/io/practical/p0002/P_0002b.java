package io.practical.p0002;
import java.util.concurrent.ForkJoinPool;

public class P_0002b {

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool(4);

		int result = 0;
		IncrementorTask1 task0 = new IncrementorTask1("Task0", true);
		result += pool.invoke(task0);
		pool.shutdown();
		System.out.println("finshed: count → " + result);

	}
}
