package io.practical.p0004.counter.impl;

import java.util.concurrent.atomic.AtomicLong;

import io.practical.p0004.counter.Counter;

public class Counter4Atomic implements Counter {

	AtomicLong count;

	public Counter4Atomic() {
		count = new AtomicLong(0);
	}

	@Override
	public void increment() {
		count.incrementAndGet();

	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " -> " + count;
	}
}
