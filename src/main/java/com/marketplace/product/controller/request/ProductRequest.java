package com.marketplace.product.controller.request;

import com.marketplace.product.domain.model.Keyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.validation.constraints.NotBlank;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {

    Long productId;
    @NotBlank(message = "El campo sku no puede ser null o vacio.")
    String sku;
    @NotBlank(message = "El campo name no puede ser null o vacio.")
    String name;
    @NotBlank(message = "El campo name no puede ser null o vacio.")
    String description;
    @NotBlank(message = "El campo keywords no puede ser null o vacio.")
    List<Keyword> keywords;
    @NotBlank(message = "El campo price no puede ser null o vacio.")
    Float price;
    String imgUrl;
    @NotBlank(message = "El campo unitAvailable no puede ser null o vacio.")
    Integer unitAvailable;
    Float weight;
    @NotBlank(message = "El campo category no puede ser null o vacio.")
    String category;
    String brand;
    Integer amountToReserve;
    Integer amountToCancel;
}
