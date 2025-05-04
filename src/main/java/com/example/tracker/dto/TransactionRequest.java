package com.example.tracker.dto;

import lombok.Data;

@Data
public class TransactionRequest {
    private Long userId;
    private String merchant;
    private Double amount;

    // Getters and Setters
}