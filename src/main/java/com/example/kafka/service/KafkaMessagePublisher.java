package com.example.kafka.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.kafka.dto.EmployeeDto;
import com.example.kafka.utils.TopicNames;

@Service
public class KafkaMessagePublisher {

	private final TopicNames topicNames;
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	public KafkaMessagePublisher(TopicNames topicNames) {
		this.topicNames = topicNames;
	}

	public void sendMessageToTopic(String message) {
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicNames.getTestTopic(), message);
		future.whenComplete((result, ex) -> {
			if (ex == null)
				System.out.println(
						"Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			else
				System.out.println("Unable to send message=[" + message + "] due to: " + ex.getMessage());
		});
	}
	
	public void sendEventToTopic(EmployeeDto employeeDto) {
		try {
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicNames.getTestTopic4(), employeeDto);
		future.whenComplete((result, ex) -> {
			if (ex == null)
				System.out.println(
						"Sent message=[" + employeeDto.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			else
				System.out.println("Unable to send message=[" + employeeDto.toString() + "] due to: " + ex.getMessage());
		});
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
}
