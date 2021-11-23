package com.marketplace.product.controller;

import com.marketplace.product.controller.request.*;
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.exception.ProductExistException;
import com.marketplace.product.exception.ProductNotExistException;
import com.marketplace.product.repositories.ProductRepository;
import com.marketplace.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.validation.annotation.Validated;


@Slf4j
@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/product/{sku}/stock/cancelReserve")
    public ResponseEntity<Product> cancelProduct(@Validated @RequestBody CancelReserveProductRequest cancelReserveProductRequest, @PathVariable("sku") String sku) {
        if (sku != null) {
            return productService.cancelReserve(cancelReserveProductRequest, sku);
        } else {
            log.error("Product not found");
            throw new ProductNotExistException("Product not found");
        }
    }

    @PostMapping(path = "/product/{sku}/stock/reserve")
    public ResponseEntity<List<Product>> reserveProduct(@Validated @RequestBody ReserveProductRequest reserveProductRequest, @PathVariable("sku") String sku) {
        if (sku != null) {
            return productService.reserveProduct(reserveProductRequest, sku);
        } else {
            log.error("Product not found");
            throw new ProductNotExistException("Product not found");
        }
    }

    @GetMapping(path = "/product/{sku}")
    public ResponseEntity<Product> retriveProduct(@PathVariable("sku") String sku) {
        return productService.getProductSku(sku);
    }

    @PutMapping(path = "/product/{sku}")
    public ResponseEntity<Product> editProduct(@Validated @RequestBody PutProductSkuRequest productRequest, @PathVariable("sku") String sku) {
        if (sku != null) {
            return productService.putProductSku(productRequest, sku);
        } else {
            log.error("The product does NOT exist");
            throw new ProductNotExistException("The product does NOT exist");
        }
    }

    @DeleteMapping(path = "/product/{sku}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("sku") String sku) {
        return productService.deleteProduct(sku);
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> getProduct() {
        return productService.getProducts();
    }

    @PostMapping(path = "/product")
    public ResponseEntity<Product> createProduct(@Validated @RequestBody PostProductRequest postProductRequest) {
        if (productRepository.findBySku(postProductRequest.getSku()) != null) {
            log.error("The sku already exist");
            throw new ProductExistException("The sku already exist");
        } else {
            return productService.createProduct(postProductRequest);
        }
    }

    @GetMapping(path = "/product/keyword")
    public ResponseEntity<Set<Product>> getKeywords(@RequestParam(value = "keys") List<String> keywords) {

        return productService.getKeywords(keywords);
    }


}
