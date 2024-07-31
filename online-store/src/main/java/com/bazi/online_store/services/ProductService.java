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

        if (Integer.valueOf(specParams.getPageIndex()) == null || Integer.valueOf(specParams.getPageIndex()) == 0 ) {
            productList = productRepository.findAll(productSpec.getProducts(specParams));
            if(productList != null && productList.size()> 0) {
                ProductListResponse prldto =  new ProductListResponse();
                prldto.setTotalCount(productList.size());
                prldto.setDataList(new ArrayList<ProductResponse>());
                for(Product product: productList) {

                    ProductResponse prdto = new ProductResponse();
                    prdto.populateDto(product);
                    prldto.getDataList().add(prdto);
                }
                return prldto;
            }

        }
        else {
            if(Integer.valueOf(specParams.getPageSize())== null || specParams.getPageSize() == 0) {

                specParams.setPageSize(defaultPageSize);

            }
            Pageable paging = PageRequest.of(specParams.getPageIndex()-1, specParams.getPageSize());
            pages = productRepository.findAll(productSpec.getProducts(specParams),paging);
            if(pages != null && pages.getContent() != null) {

                productList = pages.getContent();
                if(productList != null && productList.size() >0) {
                    ProductListResponse   prldto = new ProductListResponse();
                    prldto.setTotalPages(pages.getTotalPages());
                    prldto.setTotalCount(pages.getTotalElements());
                    prldto.setPageIndex(pages.getNumber() +1);
                    prldto.setPageSize(specParams.getPageSize());
                    prldto.setDataList(new ArrayList<ProductResponse>());
                    for(Product product: productList) {

                        ProductResponse prdto = new ProductResponse();
                        prdto.populateDto(product);
                        prldto.getDataList().add(prdto);
                    }
                    return prldto;
                }
            }

        }
        return null;
    }


}
