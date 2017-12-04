package io.practical.p0004.counter.impl;

import io.practical.p0004.counter.Counter;

public class Counter1SynchronizedBlock implements Counter {

	Long count;

	public Counter1SynchronizedBlock() {
		this.count = 0L;
	}

	@Override
	public void increment() {
		synchronized (this) {
			count++;
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " -> " + count;
	}

}
