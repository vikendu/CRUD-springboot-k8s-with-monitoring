package com.example.crud.model;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class UserEvent {
    private String action; // CREATE, UPDATE, DELETE
    private User user;
}