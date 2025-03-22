package com.DougFSiva.checkMate.exception;

public class ErroDeOperacaoComOcorrenciaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComOcorrenciaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComOcorrenciaException(String message) {
		super(message);
	}
	
}
