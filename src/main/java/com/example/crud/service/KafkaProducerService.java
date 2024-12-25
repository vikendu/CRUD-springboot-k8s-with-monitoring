package com.example.crud.service;

import com.example.crud.model.User;
import com.example.crud.model.UserEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaProducerService {
    
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }
    
    public void sendUserEvent(String action, User user) {
        try {
            UserEvent event = new UserEvent(action, user);
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("user-events", message);
            log.info("User event sent to Kafka: {}", message);
        } catch (Exception e) {
            log.error("Error sending user event to Kafka", e);
        }
    }
}
