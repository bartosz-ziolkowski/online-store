package com.bazi.online_store.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasketItem {

    private long productId;
    private String title;
    private int quantity;
    private String imageUrl;
    private double unitPrice;
    private String brandName;
    private String categoryName;
}
