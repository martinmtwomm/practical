package io.practical.p0006;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class UnsafeCounter implements Counter {

	private static final Unsafe unsafe = UnsafeHelper.getUnsafe();
	private volatile int count;
	private static long offset;
	static {
		try {
			offset = unsafe.objectFieldOffset(Integer.class.getDeclaredField("value"));
		} catch (NoSuchFieldException | SecurityException e) {
			throw new RuntimeException("Error getting offset: ", e.getCause());
		}
	}

	public UnsafeCounter() {
		count = 0;
	}

	public int getValue() {
		return unsafe.getInt(count, offset);
	}


	@Override
	public void inc() {
//		System.out.println(getValue());
		unsafe.getAndAddInt(count, offset, 1);
	}

	
//	private boolean incrementPossible(int expected, int update) {
////		System.out.println(count +", "+ expected +", "+ update);
//		return unsafe.compareAndSwapInt(count, offset, expected, update);
//	}

	public void reset() {
		unsafe.putInt(count, offset, 0);
	}

}
