package io.practical.p0006;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	private static final int ONE_THOUSAND_K = 100_000;

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		CountDownLatch latch = new CountDownLatch(ONE_THOUSAND_K);
		UnsafeCounter counter = new UnsafeCounter();
		// counter.inc();
		// System.out.println(counter.getValue());

		ExecutorService executor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < ONE_THOUSAND_K; i++) {
			executor.execute(new CounterThread(counter, latch));
		}

		try {
			latch.await();

			executor.shutdown();
			System.out.println(counter.getValue());
			counter.reset();
			System.out.println(counter.getValue());
		} catch (InterruptedException e) {
			throw new RuntimeException("interrupted ");
		}
	}
}
