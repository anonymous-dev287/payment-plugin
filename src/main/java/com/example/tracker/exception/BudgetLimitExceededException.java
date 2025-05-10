package com.example.tracker.exception;

public class BudgetLimitExceededException extends RuntimeException{
	
	public BudgetLimitExceededException(String message) {
        super(message);
    }
}
