package com.bazi.online_store.repositories;

import com.bazi.online_store.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
