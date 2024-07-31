package com.bazi.online_store.services;

import com.bazi.online_store.exceptions.BasketNotFoundException;
import com.bazi.online_store.exceptions.DeliveryMethodNotFoundException;
import com.bazi.online_store.exceptions.ProductNotFoundException;
import com.bazi.online_store.models.Basket;
import com.stripe.exception.StripeException;

public interface IPaymentService {
    Basket createOrUpdatePaymentIntent(String basketId) throws BasketNotFoundException, DeliveryMethodNotFoundException, ProductNotFoundException, StripeException;
}
