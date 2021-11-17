package com.marketplace.product.controller;

import com.marketplace.product.domain.model.Product;
import com.marketplace.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @DeleteMapping(path = "/product/{sku}")
    public Product deleteArtist(@PathVariable("sku") String sku) {
        return productService.deleteProduct(sku);
    }

}
