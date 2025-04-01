package com.DougFSiva.checkMate.mqtt;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.model.PublicadorMqtt;

@Service
public class PublisherMosquitto implements PublicadorMqtt {

	@Override
	public void enviarMensagem(String topico, String mensagem) {
		System.out.println("Enviando mensagem MQTT " + mensagem + " para tópico " + topico);
		
	}

}
