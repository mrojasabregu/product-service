package com.marketplace.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Product")
public class Product {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    //@Column(name = "PRODUCT_ID")
    //private Long productId;
    private String productId;
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private String imgUrl;
    private Integer unitAvailable;
    private Double weight;
    private String category;
    private String brand;
    private Integer amountToReserve;
    private Integer amountToCancel;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCT_KEYWORD", joinColumns = {@JoinColumn(name = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "KEYWORD_ID")})
    List<Keyword> keywords = new ArrayList<>();

}
