package com.example.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
//	@ExceptionHandler(BudgetNotFoundException.class)
//	public ResponseEntity<Object> handleBudgetNotFound(BudgetNotFoundException ex){
//		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
//	}
//	
	@ExceptionHandler(BudgetNotFoundException.class)
    public ResponseEntity<ApiError> handleBudgetNotFound(BudgetNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Budget Not Found", ex.getMessage());
    }
	
	@ExceptionHandler(BudgetLimitExceededException.class)
    public ResponseEntity<ApiError> handleLimitExceeded(BudgetLimitExceededException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Limit Exceeded", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage());
    }
	
    private ResponseEntity<ApiError> buildErrorResponse(HttpStatus status, String error, String message) {
        ApiError apiError = new ApiError(status.value(), error, message);
        return new ResponseEntity<>(apiError, status);
    }

}
