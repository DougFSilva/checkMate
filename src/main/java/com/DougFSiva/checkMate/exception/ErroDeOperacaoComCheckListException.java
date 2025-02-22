package com.DougFSiva.checkMate.exception;

public class ErroDeOperacaoComCheckListException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComCheckListException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComCheckListException(String message) {
		super(message);
	}
	
}
