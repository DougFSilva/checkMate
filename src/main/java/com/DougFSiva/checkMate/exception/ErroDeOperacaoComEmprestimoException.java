package com.DougFSiva.checkMate.exception;

public class ErroDeOperacaoComEmprestimoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComEmprestimoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComEmprestimoException(String message) {
		super(message);
	}
	
}
