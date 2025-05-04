package com.example.tracker.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseReport {
	private Long userId;
    private LocalDateTime generatedAt;
    private List<CategorySpendingDto> categoryReports;
}
