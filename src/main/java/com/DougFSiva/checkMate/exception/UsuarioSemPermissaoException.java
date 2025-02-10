package com.DougFSiva.checkMate.exception;

public class UsuarioSemPermissaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioSemPermissaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioSemPermissaoException(String message) {
		super(message);
	}
	
}
