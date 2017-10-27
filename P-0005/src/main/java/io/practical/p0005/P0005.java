package io.practical.p0005;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

import sun.misc.Unsafe;

public class P0005 {

	private static final String FILE_READ = "sample.txt";
	// = Change this according to your needs =========================
	private static final String FILE_WRITE = "E:\\sample.txt";

	public static Path getFileURIFromClasspath(String fileName) throws Exception {
		Path result = null;

		String classpath = System.getProperty("java.class.path");
		result = FileSystems.getDefault().getPath(classpath + "/" + fileName);

		return result;
	}

	public static void main(String[] args) {
		CharBuffer charBuffer = null;
		String charEncoding = null;
		MappedByteBuffer mappedByteBuffer = null;

		try {
			charEncoding = System.getProperty("file.encoding");

			// Read a file
			Path pathRead = getFileURIFromClasspath(FILE_READ);
			if (Files.exists(pathRead, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
				try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(pathRead,
						EnumSet.of(StandardOpenOption.READ))) {
					mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
					if (mappedByteBuffer != null) {
						charBuffer = Charset.forName(charEncoding).decode(mappedByteBuffer);

						// charBuffer.toString());

						// System.out.println(charBuffer.toString());
						Unsafe un = UnsafeHelper.getUnsafe();
						// un.fieldOffset(kj)

						// System.out.println(
						// UnsafeHelper.getUnsafe().getInt(0l));
						// System.out.println(
						// UnsafeHelper.getUnsafe().getChar(1));
					}
				} catch (IOException ioe) {

					ioe.printStackTrace();
				}
			} else {
				System.out.println("file does not exist");
			}

			// Write a file
			Path pathWrite = FileSystems.getDefault().getPath(FILE_WRITE);
			if (Files.notExists(pathWrite, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
				Files.createFile(pathWrite);

				try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(pathWrite, EnumSet
						.of(StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING))) {
					mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, charBuffer.length());
					if (mappedByteBuffer != null) {
						mappedByteBuffer.put(Charset.forName(charEncoding).encode(charBuffer));
					}
				} catch (IOException ioe) {

					ioe.printStackTrace();
				}
			}

		} catch (Exception e) {

			e.printStackTrace(System.err);
		}
	}

}
