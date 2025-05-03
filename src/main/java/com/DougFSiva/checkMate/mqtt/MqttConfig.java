package com.DougFSiva.checkMate.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.DougFSiva.checkMate.exception.ErroDeMQTTException;

@Configuration
public class MqttConfig {

	@Value("${mqtt.broker}")
	private String broker;
	
	@Value("${mqtt.client}")
	private String cliente;

	@Bean
	@ConditionalOnProperty(value = "mqtt.enabled", havingValue = "true", matchIfMissing = false)
	public MqttClient mqttClient() {
		try {
			MqttClient client = new MqttClient(broker, cliente, new MemoryPersistence());
			MqttConnectOptions opcoes = new MqttConnectOptions();
			opcoes.setCleanSession(true);
			client.connect(opcoes);
			return client;
		} catch (Exception e) {
			throw new ErroDeMQTTException(String.format("Erro ao configurar conexão mqtt. Erro %s", e.getMessage(), e));
		}
	
	}
}
