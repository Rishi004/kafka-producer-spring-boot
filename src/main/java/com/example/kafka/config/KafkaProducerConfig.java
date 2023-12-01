package com.example.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.kafka.utils.TopicNames;

@Configuration
public class KafkaProducerConfig {

	private final TopicNames topicNames;

	@Autowired
	public KafkaProducerConfig(TopicNames topicNames) {
		this.topicNames = topicNames;
	}

	@Bean
	public NewTopic createTopic() {
		return new NewTopic(topicNames.getTestTopic3(), 5, (short) 1);
	}
}
