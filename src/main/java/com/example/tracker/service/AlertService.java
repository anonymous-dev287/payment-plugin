package com.example.tracker.service;

import com.example.tracker.model.Category;
import org.springframework.stereotype.Service;

@Service
public class AlertService {
    public void sendThresholdAlert(Long userId, Category category, double usage) {
        System.out.printf("⚠️ User %d: %s budget reached %.2f%%\n", userId, category, usage);
    }
}