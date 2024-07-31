package com.bazi.online_store.controllers;

import com.bazi.online_store.models.Basket;
import com.bazi.online_store.models.BasketData;
import com.bazi.online_store.repositories.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    private BasketRepository basketRepository;

    @PostMapping()
    private ResponseEntity<Basket> createOrUpdateBasket(@RequestBody BasketData basketData) {
        Optional<Basket> basketOpt = basketRepository.findById(basketData.getId());
        Basket basket = null;
        basket = basketOpt.orElseGet(() -> new Basket(basketData.getId()));
        basket.setItems(basketData.getItems());
        basket.setDeliveryMethodId(basketData.getDeliveryMethodId());
        basket.setShippingPrice(basketData.getShippingPrice());
        basketRepository.save(basket);
        return ResponseEntity.ok(basket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Basket> getBasket(@PathVariable String id) {
        Optional<Basket> basketOpt = basketRepository.findById(id);

        return basketOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBasket(@PathVariable String id) {
        Optional<Basket> basketOpt = basketRepository.findById(id);
        if (basketOpt.isPresent()) {
            basketRepository.delete(basketOpt.get());
            return ResponseEntity.ok(id);
        }
        return ResponseEntity.notFound().build();
    }
}
