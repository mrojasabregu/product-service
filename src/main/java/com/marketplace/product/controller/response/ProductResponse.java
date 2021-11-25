package com.marketplace.product.controller.response;

import com.marketplace.product.domain.model.Keyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponse {

    private String productId;
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private String imgUrl;
    private Integer unitAvailable;
    private Double weight;
    private String category;
    private String brand;
    private List<Keyword> keywords;
}
