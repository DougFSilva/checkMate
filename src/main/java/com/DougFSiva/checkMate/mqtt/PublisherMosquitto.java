package com.DougFSiva.checkMate.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.model.PublicadorMqtt;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
@ConditionalOnBean(MqttClient.class)
public class PublisherMosquitto implements PublicadorMqtt {

	private final MqttClient mqttClient;

	@Override
	public void enviarMensagem(String topico, String mensagem) {

		try {
			MqttMessage mensagemMqtt = new MqttMessage(mensagem.getBytes());
			mensagemMqtt.setQos(2);
			mqttClient.publish(topico, mensagemMqtt);
			System.out.println("Enviando mensagem MQTT " + mensagem + " para tópico " + topico);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
