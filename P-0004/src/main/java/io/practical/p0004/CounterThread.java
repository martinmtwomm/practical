package io.practical.p0004;



public class CounterThread implements Runnable {

	Counter counter;
	public CounterThread(Counter counter) {
		this.counter = counter;
	}
	@Override
	public void run() {
		
		counter.inc();
	}

}
