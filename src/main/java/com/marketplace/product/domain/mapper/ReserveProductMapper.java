package com.marketplace.product.domain.mapper;

import com.marketplace.product.controller.request.ProductRequest;
import com.marketplace.product.controller.request.ReserveProductRequest;
import com.marketplace.product.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ReserveProductMapper implements Function<ReserveProductRequest, Product> {

    @Override
    public Product apply(ReserveProductRequest reserveProductRequest) {
        return Product.builder()
                .sku(reserveProductRequest.getSku())
                .unitAvailable(reserveProductRequest.getUnitAvailable())
                .amountToReserve(reserveProductRequest.getAmountToReserve())
                .build();
    }
}
