package com.marketplace.product.domain.mapper;

import com.marketplace.product.controller.request.KeywordRequest;
import com.marketplace.product.domain.model.Keyword;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class KeywordMapper implements Function<KeywordRequest, Keyword> {

    @Override
    public Keyword apply(KeywordRequest keywordRequest) {
        return Keyword.builder().keywordId(keywordRequest.getKeywordId()).name(keywordRequest.getName()).build();
    }

}
