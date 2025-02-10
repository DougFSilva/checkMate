package com.DougFSiva.checkMate.exception;

public class ErroDeOperacaoComUsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComUsuarioException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComUsuarioException(String message) {
		super(message);
	}
	
}
