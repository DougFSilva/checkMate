package com.DougFSiva.checkMate.exception;

public class EmailInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailInvalidoException(String message) {
		super(message);
	}
	
}
