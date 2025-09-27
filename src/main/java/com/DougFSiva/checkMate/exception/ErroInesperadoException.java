package com.DougFSiva.checkMate.exception;

public class ErroInesperadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroInesperadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroInesperadoException(String message) {
		super(message);
	}
	
}
