package com.bazi.online_store.repositories;

import com.bazi.online_store.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
