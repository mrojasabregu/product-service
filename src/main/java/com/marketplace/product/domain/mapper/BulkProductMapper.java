package com.marketplace.product.domain.mapper;
import com.marketplace.product.controller.request.BulkProductRequest;
import com.marketplace.product.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BulkProductMapper implements Function<BulkProductRequest, Product>{

    @Override
    public Product apply(BulkProductRequest bulkProductRequest){
        return Product.builder()
                .sku(bulkProductRequest.getSku())
                .name(bulkProductRequest.getName())
                .brand(bulkProductRequest.getBrand())
                .category(bulkProductRequest.getCategory())
                .description(bulkProductRequest.getDescription())
                .imgUrl(bulkProductRequest.getImgUrl())
                .keywords(bulkProductRequest.getKeywords())
                .price(bulkProductRequest.getPrice())
                .unitAvailable(bulkProductRequest.getUnitAvailable())
                .weight(bulkProductRequest.getWeight())
                .build();
    }
}
