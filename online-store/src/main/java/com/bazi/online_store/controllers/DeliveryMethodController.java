package com.bazi.online_store.controllers;

import com.bazi.online_store.models.order_aggregate.DeliveryMethod;
import com.bazi.online_store.repositories.DeliveryMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/deliverymethods")
public class DeliveryMethodController {

    @Autowired
    private DeliveryMethodRepository deliveryMethodRepository;

    @GetMapping
    public ResponseEntity<List<DeliveryMethod>> getDeliveryMethods() {
        List<DeliveryMethod> deliveryMethods = deliveryMethodRepository.findAll();
        return new ResponseEntity<>(deliveryMethods, HttpStatus.OK);
    }
}
