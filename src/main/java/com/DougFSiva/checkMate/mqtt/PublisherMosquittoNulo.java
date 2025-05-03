package com.DougFSiva.checkMate.mqtt;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.model.PublicadorMqtt;

@Service
@ConditionalOnMissingBean(PublisherMosquitto.class) 
public class PublisherMosquittoNulo implements PublicadorMqtt{

	@Override
	public void enviarMensagem(String topico, String mensagem) {
		
		 System.out.println("MQTT desativado - mensagem ignorada.");
		
	}

}
