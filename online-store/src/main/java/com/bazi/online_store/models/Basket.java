package com.bazi.online_store.models;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@Data
@RedisHash("BASKET")
public class Basket {

    private String id;
    private List<BasketItem> items;

    private Integer deliveryMethodId;
    private float shippingPrice;
    private String clientSecret;
    private String paymentIntentId;

    public Basket(String basketId) {
        this.id = basketId;
        items = new ArrayList<>();
    }

    public Basket() {};
}
