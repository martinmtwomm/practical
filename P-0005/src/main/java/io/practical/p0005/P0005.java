package io.practical.p0005;

import java.nio.MappedByteBuffer;

import org.openjdk.jmh.annotations.Benchmark;

public class P0005 {

	public static void main(String[] args) {

//		UnsafeCharReader readWriteUnsafestring = new UnsafeCharReader();
//		String resourceFilename = "/sample.txt";
//		MappedByteBuffer mByteBuff = readWriteUnsafestring.loadResourceIntoMappedByteBuffer(resourceFilename);
//		readWriteUnsafestring.printContent(mByteBuff);
//		byte[] result = readWriteUnsafestring.readUnsafe(mByteBuff);
//		System.out.println(new String(result));

	}

	@Benchmark
	public void testMethod0() {

		UnsafeCharReader readWriteUnsafestring = new UnsafeCharReader();
		String resourceFilename = "/sample.txt";
		MappedByteBuffer mByteBuff = readWriteUnsafestring.loadResourceIntoMappedByteBuffer(resourceFilename);
		readWriteUnsafestring.readUnsafe(mByteBuff);
	}

}
