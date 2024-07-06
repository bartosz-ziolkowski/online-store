package com.bazi.online_store.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bazi.online_store.dtos.ProductListResponse;
import com.bazi.online_store.dtos.ProductResponse;
import com.bazi.online_store.models.Product;
import com.bazi.online_store.repositories.ProductRepository;
import com.bazi.online_store.specification.ProductSpecParams;
import com.bazi.online_store.specification.ProductSpecTitleBrandCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Value("${pagination.page.size.default}")
    private Integer defaultPageSize;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSpecTitleBrandCategory productSpec;

    @Override
    public ProductResponse getProductById(long id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            ProductResponse productDto = new ProductResponse();
            productDto.populateDto(productOpt.get());
            return productDto;
        }
        return null;
    }

    @Override
    public ProductListResponse getProductList(ProductSpecParams specParams) {
        List<Product> productList = null;
        Page<Product> pages = null;

        if (Integer.valueOf(specParams.getPageIndex()) == null) {
            pages = new PageImpl<>(productRepository.findAll(productSpec.getProducts(specParams)));
        }
        else {
            if(Integer.valueOf(specParams.getPageSize()) == null || specParams.getPageSize() == 0) {
                specParams.setPageSize(defaultPageSize);
            }
            int pageIndex = Math.max(0, specParams.getPageIndex() - 1);
            Pageable paging = PageRequest.of(pageIndex, specParams.getPageSize());
            pages = productRepository.findAll(productSpec.getProducts(specParams), paging);
        }

        if(pages != null && pages.getContent() != null ) {
            productList = pages.getContent();

            if(productList != null && productList.size() > 0) {
                ProductListResponse productListDto = new ProductListResponse();
                productListDto.setTotalPages(pages.getTotalPages());
                productListDto.setTotalCount(pages.getTotalElements());
                productListDto.setPageIndex(pages.getNumber());
                productListDto.setDataList(new ArrayList<ProductResponse>());

                for(Product product: productList) {
                    ProductResponse productDto = new ProductResponse();
                    productDto.populateDto(product);
                    productListDto.getDataList().add(productDto);
                }
                return productListDto ;
            }
        }
        return null;
    }

}
