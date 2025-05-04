package com.example.tracker.dto;

import com.example.tracker.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySpendingDto {

		private Category category;
	    private double spentAmount;
	    private double limitAmount;
}
