package com.bazi.online_store.specification;

import lombok.Data;

@Data
public class ProductSpecParams {

    private int pageSize = 0;
    private String search;
    private String sort;
    private int brandId;
    private int categoryId;
    private String title;
    private final int MaxPageSize = 20;
    private int pageIndex = 0;

    public void setPageSize(int pageSize) {
        this.pageSize = Math.min(pageSize, MaxPageSize);
    }

    public void setSearch(String search) {
        this.search = search.toLowerCase();
    }
}
