// package com.example.crud.service;

// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.stereotype.Service;
// import lombok.extern.slf4j.Slf4j;

// @Service
// @Slf4j
// public class KafkaConsumerService {
    
//     @KafkaListener(topics = "user-events", groupId = "crud-group")
//     public void listen(String message) {
//         log.info("Received user event: {}", message);
//         // Add your processing logic here
//     }
// }
package com.example.crud.service;

import com.example.crud.model.User;
import com.example.crud.model.UserEvent;
import com.example.crud.model.UserAudit;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;

@Service
@Slf4j
public class KafkaConsumerService {
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private UserAuditService userAuditService;
    
    @Autowired
    private NotificationService notificationService;
    
    @KafkaListener(topics = "user-events", groupId = "crud-group")
    public void processUserEvent(String message) {
        try {
            // Parse the event
            UserEvent event = objectMapper.readValue(message, UserEvent.class);
            User user = event.getUser();
            String action = event.getAction();
            
            // Log the event
            log.info("Processing user event: {} for user: {}", action, user.getEmail());
            
            // Create audit record
            UserAudit audit = new UserAudit();
            audit.setUserId(user.getId());
            audit.setAction(action);
            audit.setTimestamp(LocalDateTime.now());
            audit.setDetails("User " + action + " operation performed");
            userAuditService.saveAudit(audit);
            
            // Send notifications based on event type
            switch (action) {
                case "CREATE":
                    notificationService.sendWelcomeEmail(user);
                    break;
                case "UPDATE":
                    notificationService.sendProfileUpdateNotification(user);
                    break;
                case "DELETE":
                    notificationService.sendAccountDeletionConfirmation(user);
                    break;
            }
            
        } catch (Exception e) {
            log.error("Error processing user event: {}", message, e);
        }
    }
}
