package com.DougFSiva.checkMate.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggerPadrao {

	private final Logger logger;

	public LoggerPadrao(Class<?> clazz) {
		this.logger = LoggerFactory.getLogger(clazz);
	}

	public void info(String mensagem) {
		logger.info(adicionarUsuarioNaMensagem(mensagem));
	}
	
	public void warno(String mensagem) {
		logger.warn(adicionarUsuarioNaMensagem(mensagem));
	}

	public void error(String mensagem) {
		logger.error(adicionarUsuarioNaMensagem(mensagem));
	}

	private String adicionarUsuarioNaMensagem(String mensagem) {
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		String usuario =  authentication != null ? authentication.getName() : "Sistema";
		return usuario + ": " + mensagem;
	}
}
