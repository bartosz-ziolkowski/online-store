package com.bazi.online_store.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductListResponse {

    private int totalPages;
    private long totalCount;
    private int pageIndex;
    private List<ProductResponse> dataList;

    public ProductListResponse() {
        dataList = new ArrayList<>();
    }
}
