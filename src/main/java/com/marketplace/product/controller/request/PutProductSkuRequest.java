package com.marketplace.product.controller.request;

import com.marketplace.product.domain.model.Keyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PutProductSkuRequest {
    Long productId;
    String sku;
    String name;
    String description;
    List<Keyword> keywords;
    Double price;
    String imgUrl;
    Integer unitAvailable;
    Double weight;
    String category;
    String brand;
    Integer amountToReserve;
    Integer amountToCancel;
}
