package io.practical.p0003;

import org.openjdk.jmh.annotations.Benchmark;

public class MyFirstBenchmark {

	@Benchmark
	public void testMethod() {
		for (int i = 0; i < 100 ; i++) {
			Math.log(i);
		}
	}
	
}
