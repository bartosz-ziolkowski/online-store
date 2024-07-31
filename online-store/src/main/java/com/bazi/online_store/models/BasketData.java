package com.bazi.online_store.models;

import jakarta.annotation.Nonnull;
import lombok.Data;

import java.util.List;

@Data
public class BasketData {
    @Nonnull
    private String Id;
    private List<BasketItem> Items;
    private Integer deliveryMethodId;
    private float shippingPrice;

    public BasketData() {}
}
