package com.example.tracker.exception;

public class BudgetNotFoundException extends RuntimeException {
	
	public BudgetNotFoundException(String msg) {
		super(msg);
	}
}
