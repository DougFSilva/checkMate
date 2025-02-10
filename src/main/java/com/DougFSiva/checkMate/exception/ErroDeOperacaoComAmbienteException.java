package com.DougFSiva.checkMate.exception;

public class ErroDeOperacaoComAmbienteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComAmbienteException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComAmbienteException(String message) {
		super(message);
	}
	
}
