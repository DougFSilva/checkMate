package com.DougFSiva.checkMate.exception;

public class ErroDeOperacaoComItemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComItemException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComItemException(String message) {
		super(message);
	}
	
}
