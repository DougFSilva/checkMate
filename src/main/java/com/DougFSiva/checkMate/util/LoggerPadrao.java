package com.DougFSiva.checkMate.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggerPadrao {

	private final Logger logger;

	public LoggerPadrao(Class<?> clazz) {
		this.logger = LoggerFactory.getLogger(clazz);
	}

	public void infoComUsuario(String mensagem) {
		logger.info(adicionarUsuarioNaMensagem(mensagem));
	}

	public void warnComUsuario(String mensagem) {
		logger.warn(adicionarUsuarioNaMensagem(mensagem));
	}

	public void errorComUsuario(String mensagem) {
		logger.error(adicionarUsuarioNaMensagem(mensagem));
	}
	
	public void infoSemUsuario(String mensagem) {
		logger.info(mensagem);
	}

	public void warnSemUsuario(String mensagem) {
		logger.warn(mensagem);
	}

	public void errorSemUsuario(String mensagem) {
		logger.error(mensagem);
	}

	private String adicionarUsuarioNaMensagem(String mensagem) {
		String usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getName();
		return String.format("[%s] %s", usuarioLogado, mensagem);
	}

}
