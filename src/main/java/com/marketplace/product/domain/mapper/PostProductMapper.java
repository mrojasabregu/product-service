package com.marketplace.product.domain.mapper;

import com.marketplace.product.controller.request.PostProductRequest;
import com.marketplace.product.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PostProductMapper implements Function<PostProductRequest, Product> {

    @Override
    public Product apply(PostProductRequest postProductRequest) {
        return Product.builder()
                .name(postProductRequest.getName())
                .brand(postProductRequest.getBrand())
                .category(postProductRequest.getCategory())
                .description(postProductRequest.getDescription())
                .imgUrl(postProductRequest.getImgUrl())
                .keywords(postProductRequest.getKeywords())
                .price(postProductRequest.getPrice())
                .sku(postProductRequest.getSku())
                .unitAvailable(postProductRequest.getUnitAvailable())
                .weight(postProductRequest.getWeight()
                        .build();
    }
}
