package com.DougFSiva.checkMate.model;

public interface EnviaMensagemMqtt {

	void enviar(String topico, String mensagem);
}
