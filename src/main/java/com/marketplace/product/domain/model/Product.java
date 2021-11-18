package com.marketplace.product.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Product")

public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;
    private String sku;
    private String name;
    private String description;
    //private List<String> keywords;
    private Float price;
    private String imgUrl;
    private Integer unitAvailable;
    private Float weight;
    private String category;
    private String brand;
    private int amountToReserve;
    private int amountToCancel;

    @ManyToMany(mappedBy = "productFK")
    Set<KeywordId> keywordFK=new HashSet<>();

}
