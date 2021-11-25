package com.marketplace.product.controller;

import com.marketplace.product.controller.Response.ProductResponse;
import com.marketplace.product.controller.request.*;
import com.marketplace.product.domain.mapper.ProductResponseMapper;
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.exception.ProductExistException;
import com.marketplace.product.exception.ProductNotExistException;
import com.marketplace.product.repositories.ProductRepository;
import com.marketplace.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.validation.annotation.Validated;


@Slf4j
@RestController
@RequestMapping("/")
public class ProductController {

    private static final String REQUEST_NO_BODY = "Request does not contain a body";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;


    @Autowired
    public ProductResponseMapper productResponseMapper;

    @PostMapping(path = "/product/stock/cancelReserve")
    public String cancelProduct(@Validated @RequestBody List<CancelReserveProductRequest> cancelRequests) {
        if (cancelRequests != null &&! cancelRequests.isEmpty ()) {
            productService.cancelReserve(cancelRequests);
            return "Reservation cancellation made";
        } else {
            return REQUEST_NO_BODY;
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
    public ProductResponse retriveProduct(@PathVariable("sku") String sku) {
        log.info("Product requested by sku: " + sku);
        return productResponseMapper.apply(productService.getProductSku(sku)) ;
    }

    @PutMapping(path = "/product/{sku}")
    public ResponseEntity<Product> editProduct(@Validated @RequestBody PutProductSkuRequest productRequest, @PathVariable("sku") String sku) {
        if (sku != null) {
            log.info("Product edited by sku: " + sku);
            return productService.putProductSku(productRequest, sku);
        } else {
            log.error("The product does NOT exist");
            throw new ProductNotExistException("The product does NOT exist");
        }
    }

    @DeleteMapping(path = "/product/{sku}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("sku") String sku) {
        log.info("Product deleted by sku: " + sku);
        return productService.deleteProduct(sku);
    }

    @GetMapping(path = "/products")
    public List<ProductResponse> getProduct() {
        log.info("All products");
        return productService.getProducts().stream().map(product -> productResponseMapper.apply(product)).collect(Collectors.toList());
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

    @RequestMapping(path = "/product/bulk", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postProductBulk (@RequestBody List<BulkProductRequest> bulkProductRequests){
        if (bulkProductRequests != null && !bulkProductRequests.isEmpty()){
            productService.postProductBulk(bulkProductRequests);
            return "Mass creation done";
        }else {
            return REQUEST_NO_BODY;
        }
    }
}
