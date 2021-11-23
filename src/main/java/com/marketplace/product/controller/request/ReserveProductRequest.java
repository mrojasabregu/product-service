package com.marketplace.product.controller.request;

import com.marketplace.product.domain.model.Keyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReserveProductRequest {
    Long productId;
    @NotBlank(message = "El campo sku no puede ser null o vacio.")
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
    @NotNull(message = "El campo amountToReserve no puede ser null o vacio.")
    @Min(value = 0, message = "The value must be positive")
    Integer amountToReserve;
    Integer amountToCancel;
}
