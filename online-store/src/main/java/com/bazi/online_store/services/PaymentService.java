package com.bazi.online_store.services;

import com.bazi.online_store.exceptions.BasketNotFoundException;
import com.bazi.online_store.exceptions.DeliveryMethodNotFoundException;
import com.bazi.online_store.exceptions.ProductNotFoundException;
import com.bazi.online_store.models.Basket;
import com.bazi.online_store.models.BasketItem;
import com.bazi.online_store.models.Product;
import com.bazi.online_store.models.order_aggregate.DeliveryMethod;
import com.bazi.online_store.repositories.BasketRepository;
import com.bazi.online_store.repositories.DeliveryMethodRepository;
import com.bazi.online_store.repositories.ProductRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentIntentUpdateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private BasketRepository basketRepository;

    @Value("${stripe_api_key}")
    private String apiKey;

    @Autowired
    private DeliveryMethodRepository deliveryMethodRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Basket createOrUpdatePaymentIntent(String basketId) throws BasketNotFoundException, DeliveryMethodNotFoundException, ProductNotFoundException, StripeException {
        Stripe.apiKey = apiKey;
        Optional<Basket> basketOpt = basketRepository.findById(basketId);
        Basket basket = basketOpt.orElseThrow(() ->
                new BasketNotFoundException("basket with id: " + basketId + " not found"));

        float shippingPrice = 0.0f;
        if(basket.getDeliveryMethodId() != null) {
            Optional<DeliveryMethod> deliveryMethodOpt = deliveryMethodRepository.findById(basket.getDeliveryMethodId());
            DeliveryMethod deliveryMethod = deliveryMethodOpt.orElseThrow(() ->
                    new DeliveryMethodNotFoundException("deliverymethod with id: " + basket.getDeliveryMethodId() + " not found"));
            shippingPrice = deliveryMethod.getPrice();
        }

        for(BasketItem item: basket.getItems()) {
            Optional<Product> productItemOpt = productRepository.findById(item.getProductId());
            Product productItem = null;
            if(productItemOpt.isPresent()) {
                productItem = productItemOpt.get();
            } else throw new ProductNotFoundException("Product with productId: " + item.getProductId() + " not found");
            if(item.getUnitPrice() != productItem.getUnitPrice()) {
                item.setUnitPrice(productItem.getUnitPrice());
            }
        }

        PaymentIntent intent = new PaymentIntent();
        List<String> paymentTypes = new ArrayList<String>();
        paymentTypes.add("card");
        if(isEmptyString(basket.getPaymentIntentId())) {
            PaymentIntentCreateParams createParams =
                    PaymentIntentCreateParams.builder()
                            .setAmount(calculateOrderAmount(basket.getItems()) + (long) shippingPrice * 100)
                            .setCurrency("usd")
                            .addAllPaymentMethodType(paymentTypes)
                            .build();
            intent = PaymentIntent.create(createParams);
            basket.setPaymentIntentId(intent.getId());
            basket.setClientSecret(intent.getClientSecret());
        } else {
            PaymentIntentUpdateParams updateParams =
                    PaymentIntentUpdateParams.builder()
                            .setAmount(calculateOrderAmount(basket.getItems()) + (long) shippingPrice * 100)
                            .build();
            intent = PaymentIntent.retrieve(basket.getPaymentIntentId());
            intent.update(updateParams);
        }

        basketRepository.save(basket);

        return basket;
    }

    private boolean isEmptyString(String data) {
        return data == null || data.isEmpty();
    }

    private long calculateOrderAmount(List<BasketItem> items) {
        long sum = items.stream()
                .mapToLong(x -> (long) x.getUnitPrice() * 100 + x.getQuantity())
                .sum();
        return sum;
    }
}
