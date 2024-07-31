package com.bazi.online_store.controllers;

import com.bazi.online_store.dtos.ProductListResponse;
import com.bazi.online_store.dtos.ProductResponse;
import com.bazi.online_store.models.Brand;
import com.bazi.online_store.models.Category;
import com.bazi.online_store.repositories.BrandRepository;
import com.bazi.online_store.repositories.CategoryRepository;
import com.bazi.online_store.services.IProductService;
import com.bazi.online_store.specification.ProductSpecParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/store")
public class ProductsController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private IProductService productService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getBrands() {
        List<Brand> brands = brandRepository.findAll();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/products")
    public ResponseEntity<ProductListResponse> getProducts(ProductSpecParams reqParams) {
        ProductListResponse productList = productService.getProductList(reqParams);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable long id) {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

}
