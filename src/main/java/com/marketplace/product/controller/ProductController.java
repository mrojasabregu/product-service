package com.marketplace.product.controller;

import com.marketplace.product.controller.request.*;
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import org.springframework.validation.annotation.Validated;


@Slf4j
@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/product/{sku}/stock/cancelReserve")
    public ResponseEntity<Product> cancelProduct(@Validated @RequestBody CancelReserveProductRequest cancelReserveProductRequest, @PathVariable("sku") String sku) {
        return productService.cancelReserve(cancelReserveProductRequest, sku);
    }

    @PostMapping(path = "/product/{sku}/stock/reserve")
    public ResponseEntity<List<Product>> reserveProduct(@Validated @RequestBody ReserveProductRequest productRequest, @PathVariable("sku") String sku) {

        return productService.reserveProduct(productRequest, sku);
    }

    @GetMapping(path = "/product/{sku}")
    public ResponseEntity<Product> retriveProduct(@PathVariable("sku") String sku) {
        return productService.getProductSku(sku);
    }

    @PutMapping(path = "/product/{sku}")
    public ResponseEntity<Product> editProduct(@Validated @RequestBody PutProductSkuRequest productRequest, @PathVariable("sku") String sku) {
        return productService.putProductSku(productRequest, sku);
    }

    @DeleteMapping(path = "/product/{sku}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("sku") String sku) {
        return productService.deleteProduct(sku);
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> getProduct() {
        return productService.getKeywords();
    }

    @PostMapping(path = "/product")
    public ResponseEntity<Product> createProduct(@Validated @RequestBody PostProductSkuRequest postProductSkuRequest) {
        return productService.createProduct(postProductSkuRequest);
    }

    @GetMapping(path = "/product/keyword")
    public ResponseEntity<Set<Product>> getKeywords(@RequestParam (value = "keys") List<String> keywords){

        return productService.getKeywords(keywords);
    }


}
