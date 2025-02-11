package com.DougFSiva.checkMate.exception;

public class ErroDeOperacaoComCompartimentoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComCompartimentoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComCompartimentoException(String message) {
		super(message);
	}
	
}
