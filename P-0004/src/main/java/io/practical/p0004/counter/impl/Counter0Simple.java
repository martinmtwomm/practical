package io.practical.p0004.counter.impl;

import io.practical.p0004.counter.Counter;

public class Counter0Simple implements Counter {

	Long count = 0L;

	public Counter0Simple() {
	}

	public void increment() {
		count++;
	}

	public String toString() {
		return getClass().getSimpleName() + " -> " + count;
	}

}
