package com.marketplace.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private List<String> keywords;
    private Float price;
    private String imgUrl;
    private Integer unitAvailable;
    private Float weight;
    private String category;
    private String brand;
    private int amountToReserve;
    private int amountToCancel;

}
