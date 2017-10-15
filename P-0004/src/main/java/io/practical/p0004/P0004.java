package io.practical.p0004;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class P0004 {
	

	

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		
		
		for (int i = 0; i < 100; i++) {
			executorService.submit(new Counter());
			System.out.println(i);
		}
		
		System.out.println("finished");
	
		executorService.shutdown();
		
	}

}
