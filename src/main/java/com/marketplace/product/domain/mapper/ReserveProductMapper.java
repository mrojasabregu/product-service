package com.marketplace.product.domain.mapper;

import com.marketplace.product.controller.request.ProductRequest;
import com.marketplace.product.controller.request.ReserveProductRequest;
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

@Component
public class ReserveProductMapper implements Function<ReserveProductRequest, Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product apply(ReserveProductRequest reserveProductRequest) {

        Product product = productRepository.findBySku(reserveProductRequest.getSku());

        return Product.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .brand(product.getBrand())
                .category(product.getCategory())
                .description(product.getDescription())
                .imgUrl(product.getImgUrl())
                .keywords(product.getKeywords())
                .price(product.getPrice())
                .sku(reserveProductRequest.getSku())
                .unitAvailable(product.getUnitAvailable())
                .weight(product.getWeight())
                .amountToReserve(reserveProductRequest.getAmountToReserve())
                .amountToCancel(product.getAmountToCancel())
                .build();
    }
}
