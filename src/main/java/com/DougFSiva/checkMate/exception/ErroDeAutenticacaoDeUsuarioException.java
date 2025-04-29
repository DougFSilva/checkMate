package com.DougFSiva.checkMate.exception;

public class ErroDeAutenticacaoDeUsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeAutenticacaoDeUsuarioException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeAutenticacaoDeUsuarioException(String message) {
		super(message);
	}
	
}
