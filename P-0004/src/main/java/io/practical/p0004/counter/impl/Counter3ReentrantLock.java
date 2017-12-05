package io.practical.p0004.counter.impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.practical.p0004.counter.Counter;

public class Counter3ReentrantLock implements Counter {

	Long count;
	static Lock lock = new ReentrantLock();

	public Counter3ReentrantLock() {
		this.count = 0L;
	}

	public void increment() {
		lock.lock();
		try {
			count++;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " -> " + count;
	}
}
