package com.bazi.online_store.models.order_aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="deliverymethods")
public class DeliveryMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="deliveryMethodId")
    private Integer deliveryMethodId;
    @Column(nullable = false)
    private String shortName;
    @Column(nullable = false)
    private String deliveryTime;
    private String description;
    @Column(nullable = false)
    private float price;

    public DeliveryMethod() {}

    public DeliveryMethod(String shortName, String deliveryTime, String description, float price) {
        super();
        this.shortName = shortName;
        this.deliveryTime = deliveryTime;
        this.description = description;
        this.price = price;
    }
}
