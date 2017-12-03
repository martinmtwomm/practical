package io.practical.p0005;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

import javax.management.RuntimeErrorException;

import io.practical.p0005.exception.BufferFieldException;
import io.practical.p0005.exception.FileLoaderException;
import io.practical.p0005.util.UnsafeHelper;
import io.practical.p0005.util.Utils;
import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class UnsafeCharReader {
	// 1 ASCII character = 8 Bit (e.g. A - B - C - D -> 01000001 01000010 01000011
	// 01000100 )

	// value indicates how much bytes every type takes (e.g. LONG -> 8 Bytes = 8x8
	// bits = 64 bit)
	// LONG contains up to 4 characters
	private static final int BYTE = 1;
	private static final int SHORT = 2;
	private static final int INT = 4;
	private static final int LONG = 8;
	private static final String BUFFER_FIELD_ADDRESS = "address";
	private byte[] result;
	private long index;
	int countRest;
	int pos;

	public UnsafeCharReader() {

	}

	/**
	 * 
	 * @param unsafe
	 * @param adr
	 * @param limit
	 *            : represents the length of the buffer (e.g. limit = 50 -> 50 bytes
	 *            -> 50 characters in buffer)
	 * @param printOutput
	 *
	 */
	private byte[] extractCharactersViaUnsafe(Unsafe unsafe, long adr, int limit) {

		result = new byte[limit + 4];

		index = adr;
		countRest = limit;
		pos = 0;

		// @formatter:off	
		int countLoopsLong = limit / LONG;
		for (int i = 0; i < countLoopsLong; i++)
			readBufferContent(unsafe, LONG,result);	

		//at this point, there are max 7 bytes left
		//use the maximum possible datatype to extract the value
		// Example 1: 7 bytes left -> 1x INT , 1x Short , 1x Byte
		// Example 2: 5 bytes left -> 1x INT , 0x Short , 1x Byte
		if (countRest >= INT)  readBufferContent(unsafe,  INT,result);
		if (countRest >= SHORT) readBufferContent(unsafe,SHORT,result);
		if (countRest >= BYTE) readBufferContent(unsafe,BYTE,result);
		// @formatter:on
		return result;
	}

	private void readBufferContent(Unsafe unsafe, int dataType, byte[] result) {
		byte[] bContent = getValueForDataType(unsafe, index, dataType);

		this.index += dataType;
		this.countRest -= dataType;
		System.arraycopy(bContent, 0, this.result, pos, bContent.length);
		this.pos += dataType;
	}

	private byte[] getValueForDataType(Unsafe unsafe, long index, int dataType) {

		// @formatter:off
		if(dataType == LONG) {return Utils.longToBytes(unsafe.getLong(index));};
		if(dataType == INT) {return Utils.intToBytes(unsafe.getInt(index));};
		if(dataType == SHORT) {return Utils.shortToBytes(unsafe.getShort(index));};
		if(dataType == BYTE) {return new byte[]{unsafe.getByte(index)};};
		throw new RuntimeErrorException(null, "");
		// @formatter:on
	}

	public byte[] readUnsafe(MappedByteBuffer buffer) {
		Unsafe unsafe = UnsafeHelper.getUnsafe();
		try {
			// get the value of the "address" variable declared in Buffer.class
			long bufferAddress = unsafe.objectFieldOffset(Buffer.class.getDeclaredField(BUFFER_FIELD_ADDRESS));
			// get the pointer to the start of "the file"
			long memoryAddress = (long) unsafe.getLong(buffer, bufferAddress);
			return extractCharactersViaUnsafe(unsafe, memoryAddress, buffer.limit());
		} catch (NoSuchFieldException e) {
			throw new BufferFieldException("The field [" + BUFFER_FIELD_ADDRESS + "]");
		}

	}

	public MappedByteBuffer loadResourceIntoMappedByteBuffer(String resourceFilename) {
		Path path = Utils.getResourcePath(P0005.class, resourceFilename);
		return loadResourceIntoMappedByteBuffer(path);
	}

	private MappedByteBuffer loadResourceIntoMappedByteBuffer(Path resourceFilePath) {
		// Load source file from resources folder into MappedByteBuffer
		if (Files.exists(resourceFilePath, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			return getMappedByBufferFromFile(resourceFilePath);
		} else {
			throw new FileLoaderException("unable to proceed: File does not exist");
		}
	}

	private MappedByteBuffer getMappedByBufferFromFile(Path resourceFilePath) {
		try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(resourceFilePath,
				EnumSet.of(StandardOpenOption.READ))) {
			return fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
		} catch (IOException ioe) {
			throw new FileLoaderException("Error occured while reading file into MappedByteBuffer", ioe.getCause());
		}

	}

	public void printContent(MappedByteBuffer buffer) {
		if (buffer != null) {
			String charEncoding = System.getProperty("file.encoding");
			CharBuffer charBuffer = Charset.forName(charEncoding).decode(buffer);
			System.out.println(charBuffer.toString());
		}

	}

}
