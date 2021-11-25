package com.marketplace.product.domain.mapper;

import com.marketplace.product.controller.response.ProductResponse;
import com.marketplace.product.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductResponseMapper implements Function<Product, ProductResponse > {

    @Override
    public ProductResponse apply(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .brand(product.getBrand())
                .category(product.getCategory())
                .description(product.getDescription())
                .imgUrl(product.getImgUrl())
                .keywords(product.getKeywords())
                .price(product.getPrice())
                .sku(product.getSku())
                .unitAvailable(product.getUnitAvailable())
                .weight(product.getWeight())
                .build();
    }



}
