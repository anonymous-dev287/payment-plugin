package com.example.tracker.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transaction {
    @Id @GeneratedValue
    private Long id;
    private Long userId;
    private String merchant;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private Category category;
    private LocalDateTime timestamp;

    // Getters and Setters
}