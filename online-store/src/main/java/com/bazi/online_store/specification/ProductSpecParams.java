package com.bazi.online_store.specification;

import lombok.Data;

@Data
public class ProductSpecParams {

    private int pageSize;
    private String search;
    private Long sort;
    private Long brandId;
    private Long categoryId;
    private final int MaxPageSize = 20;

    public void setPageSize(int pageSize) {
        this.pageSize = Math.min(pageSize, MaxPageSize);
    }

    public void setSearch(String search) {
        this.search = search.toLowerCase();
    }
}
