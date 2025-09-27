package com.DougFSiva.checkMate.exception;

public class TokenInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenInvalidoException(String message) {
		super(message);
	}
	
}
