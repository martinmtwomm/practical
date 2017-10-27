package io.practical.p0004;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class Bench2 {
	
//	@Benchmark
//	@OutputTimeUnit(TimeUnit.SECONDS)
//	public void incrementSimple() {
//		Cs counter = new Cs();
//		for (int i = 0; i < 1000000; i++) {
//			counter.increment();
//		}
//	}
//	
//	@Benchmark
//	@OutputTimeUnit(TimeUnit.SECONDS)
//	public void incrementMethodSync() {
//		CsMSync counter = new CsMSync();
//		for (int i = 0; i < 1000000; i++) {
//			counter.increment();
//		}
//	}
	
//	@Benchmark
//	@OutputTimeUnit(TimeUnit.SECONDS)
//	public void incrementSyncBlock() {
//		CsBlockSync counter = new CsBlockSync();
//		for (int i = 0; i < 1000000; i++) {
//			counter.increment();
//		}
//	}
	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void increment1MnotSynchronizedMonoThread() {
		CounterThread counter = new CounterThread(new Cs());
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 1000000; i++) {
			counter.run();
		}
	}
	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void increment2() {
		CounterThread counter = new CounterThread(new CsMSync());
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 1000000; i++) {
			counter.run();
		}
	}
	
	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void increment3() {
		CounterThread counter = new CounterThread(new CsBlockSync());
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 1000000; i++) {
			counter.run();
		}
	}
	
	
//	Foo counter = new Foo();
//	@Benchmark
//	@OutputTimeUnit(TimeUnit.SECONDS)
//	public void increment1MnotSynchronizedMonoThread() {
//		ExecutorService executor = Executors.newFixedThreadPool(4);
//		for (int i = 0; i < 1000000; i++) {
//			executor.submit(counter);
//		}
//	}
//	
//	Foo1 c1 = new Foo1();
//	@Benchmark
//	@OutputTimeUnit(TimeUnit.SECONDS)
//	public void increment1MSynchronizedMonoThread() {
//		ExecutorService executor = Executors.newFixedThreadPool(4);
//		for (int i = 0; i < 1000000; i++) {
//			executor.submit(c1);
//		}
//	}

	

}
