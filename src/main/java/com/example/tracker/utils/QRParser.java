package com.example.tracker.utils;

import com.example.tracker.model.Category;

public class QRParser {
    public static Category getCategoryFromMerchant(String merchant) {
        if (merchant.toLowerCase().contains("cafe")) return Category.FOOD;
        if (merchant.toLowerCase().contains("shop")) return Category.SHOPPING;
        if (merchant.toLowerCase().contains("movie")) return Category.ENTERTAINMENT;
        return Category.BEVERAGES;
    }
}