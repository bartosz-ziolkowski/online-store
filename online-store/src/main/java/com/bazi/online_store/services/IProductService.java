package com.bazi.online_store.services;

import com.bazi.online_store.dtos.ProductListResponse;
import com.bazi.online_store.dtos.ProductResponse;
import com.bazi.online_store.specification.ProductSpecParams;

public interface IProductService {

    public ProductResponse getProductById(long productId);
    public ProductListResponse getProductList(ProductSpecParams specParams);

}
