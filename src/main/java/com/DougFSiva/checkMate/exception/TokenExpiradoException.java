package com.DougFSiva.checkMate.exception;

public class TokenExpiradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenExpiradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenExpiradoException(String message) {
		super(message);
	}
	
}
