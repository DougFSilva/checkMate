package com.DougFSiva.checkMate.exception;

public class ErroDeMQTTException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeMQTTException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeMQTTException(String message) {
		super(message);
	}
	
}