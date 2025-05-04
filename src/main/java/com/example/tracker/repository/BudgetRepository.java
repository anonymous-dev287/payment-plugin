package com.example.tracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tracker.model.Budget;
import com.example.tracker.model.Category;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByUserIdAndCategory(Long userId, Category category);
    List<Budget> findByUserIdOrderBySpentAmountDesc(Long userId);
    List<Budget> findByUserId(Long userId);
}