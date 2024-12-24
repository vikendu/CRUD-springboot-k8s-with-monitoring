package com.example.crud.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumerService {
    
    @KafkaListener(topics = "user-events", groupId = "crud-group")
    public void listen(String message) {
        log.info("Received user event: {}", message);
        // Add your processing logic here
    }
}