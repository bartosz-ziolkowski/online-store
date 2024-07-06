package com.bazi.online_store.repositories;

import com.bazi.online_store.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    public Page<Product> findAll(Specification<Product> spec, Pageable pageable);
    public List<Product> findAll(Specification<Product> spec);
}
