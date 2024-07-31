package com.bazi.online_store.services;

import com.bazi.online_store.models.Basket;

public interface IPaymentInterface {
    Basket createOrUpdatePaymentIntent(String basketId);
}
