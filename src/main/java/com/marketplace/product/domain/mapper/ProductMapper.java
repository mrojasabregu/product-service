package com.marketplace.product.domain.mapper;


import com.marketplace.product.controller.request.ProductRequest;
import com.marketplace.product.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductMapper implements Function<ProductRequest,Product>{

    @Override
    public Product apply(ProductRequest productRequest){
        return Product.builder()
                .productId(productRequest.getProductId())
                .name(productRequest.getName())
                .brand(productRequest.getBrand())
                .category(productRequest.getCategory())
                .description(productRequest.getDescription())
                .imgUrl(productRequest.getImgUrl())
                //.keywords(productRequest.getKeywords())
                .price(productRequest.getPrice())
                .sku(productRequest.getSku())
                .unitAvailable(productRequest.getUnitAvailable())
                .weight(productRequest.getWeight())
                .amountToReserve(productRequest.getAmountToReserve())
                .amountToCancel(productRequest.getAmountToCancel())
                .build();
    }
}


