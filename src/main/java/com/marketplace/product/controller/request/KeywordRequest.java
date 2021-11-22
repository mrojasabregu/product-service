package com.marketplace.product.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class KeywordRequest {

    private Long keywordId;
    //@NotBlank(message = "El campo name no puede ser null o vacio.")
    private List<String> name;
}
