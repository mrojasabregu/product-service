package com.marketplace.product.controller;

import com.marketplace.product.domain.model.Product;
import com.marketplace.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


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

    @GetMapping(path = "/products")
    public List<Product> getProduct() {
        return productService.getProducts();
    }




}
