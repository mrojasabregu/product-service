package com.marketplace.product.controller.request;
import com.marketplace.product.domain.model.Keyword;
import com.marketplace.product.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BulkProductRequest {
    @NotBlank(message = "The field sku cannot be empty or null")
    String sku;
    @NotBlank(message = "The field name cannot be empty or null")
    String name;
    @NotBlank(message = "The field description cannot be empty or null")
    String description;
    @NotNull(message = "The field keywords cannot be empty or null")
    List<Keyword> keywords;
    @NotNull(message = "The field price cannot be empty or null")
    BigDecimal price;
    String imgUrl;
    @NotNull(message = "The field unitAvailable cannot be empty or null")
    Integer unitAvailable;
    Double weight;
    @NotBlank(message = "The field caterory cannot be empty or null")
    String category;
    String brand;
}
