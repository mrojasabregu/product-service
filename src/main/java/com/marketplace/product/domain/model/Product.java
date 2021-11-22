package com.marketplace.product.domain.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Product")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;
    private String sku;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Integer unitAvailable;
    private Double weight;
    private String category;
    private String brand;
    private Integer amountToReserve;
    private Integer amountToCancel;

    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCT_KEYWORD",joinColumns = {@JoinColumn(name = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "KEYWORD_ID")})
    List<Keyword> keywords = new ArrayList<>();

}
