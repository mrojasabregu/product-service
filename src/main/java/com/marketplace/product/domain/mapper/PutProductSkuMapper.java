package com.marketplace.product.domain.mapper;

import com.marketplace.product.controller.request.CancelReserveProductRequest;
import com.marketplace.product.controller.request.PutProductSkuRequest;
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PutProductSkuMapper implements Function<PutProductSkuRequest, Product> {
    @Autowired
    private ProductRepository productRepository;

    public Product apply(PutProductSkuRequest putProductSkuRequest) {

        Product product = productRepository.findBySku(putProductSkuRequest.getSku());

        return Product.builder()
                .productId(product.getProductId())
                .name(putProductSkuRequest.getName())
                .brand(putProductSkuRequest.getBrand())
                .category(putProductSkuRequest.getCategory())
                .description(putProductSkuRequest.getDescription())
                .imgUrl(putProductSkuRequest.getImgUrl())
                .keywords(putProductSkuRequest.getKeywords())
                .price(putProductSkuRequest.getPrice())
                .sku(putProductSkuRequest.getSku())
                .unitAvailable(putProductSkuRequest.getUnitAvailable())
                .weight(putProductSkuRequest.getWeight())
                .amountToReserve(product.getAmountToReserve())
                .amountToCancel(product.getAmountToCancel())
                .build();
    }
}
