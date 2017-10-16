package io.practical.p0004;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterReentrantLock implements Runnable {

	private int count;
	private Lock lock = new ReentrantLock();

	public CounterReentrantLock(int count) {
		this.count = count;
	}

	void increment() {
		this.count++;
	}

	@Override
	public void run() {
		lock.lock();
		try {
			increment();
		} finally {
			lock.unlock();
		}

	}

}
