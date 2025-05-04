package com.example.tracker.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tracker.dto.CategorySpendingDto;
import com.example.tracker.dto.ExpenseReport;
import com.example.tracker.dto.TransactionRequest;
import com.example.tracker.model.Budget;
import com.example.tracker.model.Category;
import com.example.tracker.model.Transaction;
import com.example.tracker.repository.BudgetRepository;
import com.example.tracker.repository.TransactionRepository;
import com.example.tracker.utils.QRParser;

import lombok.Data;

@Service
@Data
public class TransactionService {

    @Autowired private TransactionRepository transactionRepo;
    @Autowired private BudgetRepository budgetRepo;
    @Autowired private AlertService alertService;

    public void handleTransaction(TransactionRequest request) {
        Category category = QRParser.getCategoryFromMerchant(request.getMerchant());
        Budget budget = budgetRepo.findByUserIdAndCategory(request.getUserId(), category).orElseThrow(() -> new RuntimeException("Budget not found for user and category"));
        double newSpent = budget.getSpentAmount() + request.getAmount();
        budget.setSpentAmount(newSpent);
        budgetRepo.save(budget);
        Transaction tx = new Transaction();
        tx.setUserId(request.getUserId());
        tx.setMerchant(request.getMerchant());
        tx.setAmount(request.getAmount());
        tx.setCategory(category);
        tx.setTimestamp(LocalDateTime.now());
        transactionRepo.save(tx);
        double usedPercentage = (newSpent / budget.getLimitAmount()) * 100;
        if (usedPercentage >= 80.0) {
            alertService.sendThresholdAlert(request.getUserId(), category, usedPercentage);
        }
    }
    public void createBudget(Budget brequest) {
    	brequest.setCreatedAt(LocalDateTime.now());
    	if (brequest.getValidUntil() == null) {
    	    brequest.setValidUntil(LocalDateTime.now().plusMonths(1));
    	}
    	
    	budgetRepo.save(brequest);
    	System.out.println("Budget is created");
        
    }
    public List<CategorySpendingDto> getSortedCategorySpending(Long userId) {
        List<Budget> budgets = budgetRepo.findByUserIdOrderBySpentAmountDesc(userId);
        return budgets.stream()
            .map(b -> new CategorySpendingDto(b.getCategory(), b.getSpentAmount(), b.getLimitAmount()))
            .collect(Collectors.toList());
    }
    
    public void renewExpiredBudgets(Long userId, List<Category> updatedCategories) {
        List<Budget> existingBudgets = budgetRepo.findByUserId(userId);
        Set<Category> updatedCategorySet = new HashSet<>(updatedCategories);

        // Renew expired budgets
        for (Budget b : existingBudgets) {
            if (b.getValidUntil().isBefore(LocalDateTime.now())) {
                b.setCreatedAt(LocalDateTime.now());
                b.setValidUntil(LocalDateTime.now().plusMonths(1));
                b.setSpentAmount(0.0); // Reset spend
                budgetRepo.save(b);
            }
        }

        // Add new categories (not already existing)
        for (Category cat : updatedCategorySet) {
            boolean exists = existingBudgets.stream()
                .anyMatch(b -> b.getCategory().equals(cat));
            if (!exists) {
                Budget newBudget = new Budget();
                newBudget.setUserId(userId);
                newBudget.setCategory(cat);
                newBudget.setLimitAmount(0.0); // default
                newBudget.setSpentAmount(0.0);
                newBudget.setCreatedAt(LocalDateTime.now());
                newBudget.setValidUntil(LocalDateTime.now().plusMonths(1));
                budgetRepo.save(newBudget);
            }
        }

        // Remove categories if no longer desired
        for (Budget b : existingBudgets) {
            if (!updatedCategorySet.contains(b.getCategory())) {
                budgetRepo.delete(b);
            }
        }
    }

    public ExpenseReport clearBudgetsAndGenerateReport(Long userId) {
        List<Budget> budgets = budgetRepo.findByUserId(userId);
        List<CategorySpendingDto> reportData = new ArrayList<>();

        for (Budget b : budgets) {
            reportData.add(new CategorySpendingDto(b.getCategory(), b.getSpentAmount(), b.getLimitAmount()));
            b.setSpentAmount(0.0);  // Reset spent amount
            b.setCreatedAt(LocalDateTime.now());
            b.setValidUntil(LocalDateTime.now().plusMonths(1));
            budgetRepo.save(b);
        }

        return new ExpenseReport(userId, LocalDateTime.now(), reportData);
    }

    
}