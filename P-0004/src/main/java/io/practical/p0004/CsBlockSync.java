package io.practical.p0004;

public class CsBlockSync implements Counter{

	private int count=0;

	public CsBlockSync() {

	}


	@Override
	public void inc() {
		synchronized (this) {
			this.count++;
		}
	}

}
