package com.DougFSiva.checkMate.exception;

public class SenhaNaoAlteradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SenhaNaoAlteradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public SenhaNaoAlteradaException(String message) {
		super(message);
	}
	
}
