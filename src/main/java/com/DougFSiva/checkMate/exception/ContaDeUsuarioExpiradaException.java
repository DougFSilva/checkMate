package com.DougFSiva.checkMate.exception;

public class ContaDeUsuarioExpiradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ContaDeUsuarioExpiradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContaDeUsuarioExpiradaException(String message) {
		super(message);
	}
	
}
