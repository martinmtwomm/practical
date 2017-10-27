package io.practical.p0004;

public class Cs  implements Counter{

	private volatile int count=0;

	public Cs() {
	
	}

	@Override
	public void inc() {
		this.count++;
		
	}

	

}
