package com.example.crud.service;

import com.example.crud.model.UserAudit;
import com.example.crud.repository.UserAuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserAuditService {
    
    @Autowired
    private UserAuditRepository userAuditRepository;
    
    public void saveAudit(UserAudit audit) {
        userAuditRepository.save(audit);
    }
}