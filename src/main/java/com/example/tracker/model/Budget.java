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
public class Budget {
    @Id @GeneratedValue
    private Long id;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Double limitAmount;
    private Double spentAmount;

    private LocalDateTime createdAt;
    private LocalDateTime validUntil;
    
    }