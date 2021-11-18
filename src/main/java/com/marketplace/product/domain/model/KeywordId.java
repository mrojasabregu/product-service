package com.marketplace.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
//@Entity
//@Table(name = "Keywords")
@Embeddable
public class KeywordId implements Serializable {

    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Column(name = "KEYWORD")
    private String keyword;




}
