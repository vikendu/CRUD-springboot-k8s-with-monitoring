package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crud.model.UserAudit;

public interface UserAuditRepository extends JpaRepository<UserAudit, Long> {
}