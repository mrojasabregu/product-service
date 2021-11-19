package com.marketplace.product.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class KeywordRequest {

    private Long keywordId;
    private String name;
}
