package io.practical.p0004.counter.impl;

public class Counter2MethodSynchronized {

	Long count;

	public Counter2MethodSynchronized() {
		this.count = 0L;
	}

	public synchronized void increment() {
		count++;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " -> " + count;
	}
}
