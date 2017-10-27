package io.practical.p0004;

public class CsMSync implements Counter {

	private int count = 0;

	public CsMSync() {

	}

	@Override
	public void inc() {
		this.count++;

	}

}
