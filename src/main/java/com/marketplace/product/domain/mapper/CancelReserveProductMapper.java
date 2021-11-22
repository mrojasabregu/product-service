package com.marketplace.product.domain.mapper;

import com.marketplace.product.controller.request.CancelReserveProductRequest;
import com.marketplace.product.controller.request.ReserveProductRequest;
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CancelReserveProductMapper implements Function<CancelReserveProductRequest, Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product apply(CancelReserveProductRequest cancelReserveProductRequest) {

        Product product = productRepository.findBySku(cancelReserveProductRequest.getSku());

        return Product.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .brand(product.getBrand())
                .category(product.getCategory())
                .description(product.getDescription())
                .imgUrl(product.getImgUrl())
                .keywords(product.getKeywords())
                .price(product.getPrice())
                .sku(cancelReserveProductRequest.getSku())
                .unitAvailable(product.getUnitAvailable())
                .weight(product.getWeight())
                .amountToReserve(product.getAmountToReserve())
                .amountToCancel(cancelReserveProductRequest.getAmountToCancel())
                .build();
    }
}
