package io.practical.p0005.util;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.practical.p0005.P0005;
import io.practical.p0005.exception.ResourceNotFoundException;

public class Utils {
	public static byte[] longToBytes(long l) {
		byte[] result = new byte[8];
		for (int i = 0; i <= 7; i++) {
			result[i] = (byte) (l & 0xFF);
			l >>= 8;
		}
		return result;
	}

	public static byte[] intToBytes(int i) {
		byte[] result = new byte[4];

		result[0] = (byte) (i >> 24);
		result[1] = (byte) (i >> 16);
		result[2] = (byte) (i >> 8);
		result[3] = (byte) (i /* >> 0 */);
		return result;

	}

	public static byte[] shortToBytes(short x) {

		return new byte[] { (byte) (x & 0xff), (byte) ((x >> 8) & 0xff) };
	}

	public static Path getResourcePath(Class<P0005> resourceClass, String resourceFilename) {
		URL url = resourceClass.getResource(resourceFilename);
		try {
			return Paths.get(url.toURI());
		} catch (URISyntaxException e) {
			throw new ResourceNotFoundException("Resource not found", e.getCause());
		}
	}
}
