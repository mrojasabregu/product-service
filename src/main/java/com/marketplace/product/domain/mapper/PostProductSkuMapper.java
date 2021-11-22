package com.marketplace.product.domain.mapper;

import com.marketplace.product.controller.request.PostProductSkuRequest;
import com.marketplace.product.controller.request.PutProductSkuRequest;
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PostProductSkuMapper implements Function<PostProductSkuRequest, Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product apply(PostProductSkuRequest postProductSkuRequest) {
        Product product = productRepository.findBySku(postProductSkuRequest.getSku());

        return Product.builder()
                .productId(product.getProductId())
                .name(postProductSkuRequest.getName())
                .brand(postProductSkuRequest.getBrand())
                .category(postProductSkuRequest.getCategory())
                .description(postProductSkuRequest.getDescription())
                .imgUrl(postProductSkuRequest.getImgUrl())
                .keywords(postProductSkuRequest.getKeywords())
                .price(postProductSkuRequest.getPrice())
                .sku(postProductSkuRequest.getSku())
                .unitAvailable(postProductSkuRequest.getUnitAvailable())
                .weight(postProductSkuRequest.getWeight())
                .amountToReserve(product.getAmountToReserve())
                .amountToCancel(product.getAmountToCancel())
                .build();
    }
}
