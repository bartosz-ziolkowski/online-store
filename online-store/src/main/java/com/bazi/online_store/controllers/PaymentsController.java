package com.bazi.online_store.controllers;

import com.bazi.online_store.exceptions.BasketNotFoundException;
import com.bazi.online_store.exceptions.DeliveryMethodNotFoundException;
import com.bazi.online_store.exceptions.ProductNotFoundException;
import com.bazi.online_store.models.Basket;
import com.bazi.online_store.services.IPaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("/{basketId}")
    public ResponseEntity<Basket> createOrUpdatePaymentIntent(@PathVariable String basketId) throws StripeException, BasketNotFoundException, DeliveryMethodNotFoundException, ProductNotFoundException {
        Basket basket = paymentService.createOrUpdatePaymentIntent(basketId);

        if(basket == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(basket);
    }
}
