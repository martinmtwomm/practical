package io.practical.p0005.exception;

public class FileLoaderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7962052894155600467L;

	public FileLoaderException() {
		super();
	}

	public FileLoaderException(String message) {
		super(message);
	}

	public FileLoaderException(String message, Throwable cause) {
		super(message, cause);
	}

}
