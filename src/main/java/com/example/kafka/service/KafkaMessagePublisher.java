package com.example.kafka.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.kafka.utils.TopicNames;

@Service
public class KafkaMessagePublisher {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	private TopicNames topicNames;

	public void sendMessageToTopic(String message) {
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicNames.getTestTopic2(), message);
		future.whenComplete((result, ex) -> {
			if (ex == null)
				System.out.println(
						"Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			else
				System.out.println("Unable to send message=[" + message + "] due to: " + ex.getMessage());
		});
	}
}
