package com.bazi.online_store.repositories;

import com.bazi.online_store.models.order_aggregate.DeliveryMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryMethodRepository extends JpaRepository<DeliveryMethod, Integer> {
}
