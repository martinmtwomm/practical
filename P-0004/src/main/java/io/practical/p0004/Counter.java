package io.practical.p0004;

public class Counter implements Runnable {

	int counter = 0;

	public Counter() {
		
	}

	void increment() {
		this.counter++;
	}

//	synchronized void incrementSyncMethod() {
//		this.counter++;
//	}
//
//	void incrementSyncInside() {
//		synchronized (this) {
//			this.counter++;
//		}
//	}

	@Override
	public void run() {
		increment();
		System.out.println("counter: " + counter);
	}

}
