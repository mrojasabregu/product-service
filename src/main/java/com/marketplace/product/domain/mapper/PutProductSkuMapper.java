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

    public Product apply(PutProductSkuRequest putProductSkuRequest) {

        return Product.builder()
                .productId(putProductSkuRequest.getProductId())
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
                .build();
    }
}
