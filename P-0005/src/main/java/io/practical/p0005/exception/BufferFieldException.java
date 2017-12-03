package io.practical.p0005.exception;

public class BufferFieldException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7962052894155600467L;

	public BufferFieldException() {
		super();
	}

	public BufferFieldException(String message) {
		super(message);
	}

	public BufferFieldException(String message, Throwable cause) {
		super(message, cause);
	}

}
