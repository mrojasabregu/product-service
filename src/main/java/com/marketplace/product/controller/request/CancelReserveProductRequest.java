package com.marketplace.product.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CancelReserveProductRequest {

    String sku;
    Integer unitAvailable;
    Integer amountToCancel;

}
