package com.example.tracker.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tracker.dto.CategorySpendingDto;
import com.example.tracker.dto.ExpenseReport;
import com.example.tracker.dto.TransactionRequest;
import com.example.tracker.model.Budget;
import com.example.tracker.model.Category;
import com.example.tracker.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired private TransactionService transactionService;

    @PostMapping("maketransaction")
    public ResponseEntity<String> createTransaction(@RequestBody TransactionRequest request) {
        transactionService.handleTransaction(request);
        return ResponseEntity.ok("Transaction processed.");
    }
    
    @PostMapping("createbudget")
    public ResponseEntity<String> createBudget(@RequestBody Budget budget){
    	
    	transactionService.createBudget(budget);
    	
    	return ResponseEntity.ok("Budget Created successfully ");
    }
    
    @GetMapping("/categories")
    public ResponseEntity<String> categoryList(){
    	String categories = Arrays.stream(Category.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));

    	return new ResponseEntity<>(categories, HttpStatus.OK);
    	
    }
    
    @GetMapping("/top-categories")
    public ResponseEntity<List<CategorySpendingDto>> getTopCategories(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(transactionService.getSortedCategorySpending(userId));
    }
    
    @PostMapping("/renew")
    public ResponseEntity<String> renewBudgets(
        @RequestParam Long userId,
        @RequestBody List<Category> updatedCategories) {
        transactionService.renewExpiredBudgets(userId, updatedCategories);
        return ResponseEntity.ok("Budgets renewed and categories updated.");
    }
    @PostMapping("/clear-and-report")
    public ResponseEntity<ExpenseReport> clearAndReport(@RequestParam ("userId")Long userId) {
        ExpenseReport report = transactionService.clearBudgetsAndGenerateReport(userId);
        return ResponseEntity.ok(report);
    }

}