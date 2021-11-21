package com.marketplace.product.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReserveProductRequest {

    String sku;
    Integer unitAvailable;
    Integer amountToReserve;
}
