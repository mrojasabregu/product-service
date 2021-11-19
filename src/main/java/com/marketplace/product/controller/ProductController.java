package com.marketplace.product.controller;

import com.marketplace.product.controller.request.KeywordRequest;
import com.marketplace.product.controller.request.ProductRequest;
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.repositories.ProductRepository;
import com.marketplace.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping(path = "/product/{sku}/stock/cancelReserve")
    public Product cancelProduct(@Validated @RequestBody ProductRequest productRequest, @PathVariable("sku") String sku) {
        return productService.cancelReserve(productRequest, sku);
    }

    @GetMapping(path = "/product/{sku}")
    public Product retriveProduct(@PathVariable("sku") String sku) {
        return productService.getProductSku(sku);
    }

    @PutMapping(path = "/product/{sku}")
    public Product editProduct(@Validated @RequestBody ProductRequest request, @PathVariable("sku") String sku) {
        return productService.putProductSku(request, sku);
    }

    @DeleteMapping(path = "/product/{sku}")
    public Product deleteProduct(@PathVariable("sku") String sku) {
        return productService.deleteProduct(sku);
    }

    @GetMapping(path = "/products")
    public List<Product> getProduct() {
        return productService.getProducts();
    }

    @PostMapping(path = "/product")
    public Product createProduct(@Validated @RequestBody ProductRequest productRequest) {
        return productService.CreateProduct(productRequest);
    }


}
