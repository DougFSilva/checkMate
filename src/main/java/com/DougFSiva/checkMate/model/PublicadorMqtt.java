package com.DougFSiva.checkMate.model;

public interface PublicadorMqtt {

	void enviarMensagem(String topico, String mensagem);
}
