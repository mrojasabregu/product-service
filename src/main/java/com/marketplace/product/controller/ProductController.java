package com.marketplace.product.controller;

import com.marketplace.product.controller.request.*;
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

    @PostMapping(path = "/product/{sku}/stock/cancelReserve")
    public ResponseEntity<Product> cancelProduct(@Validated @RequestBody List<CancelReserveProductRequest> cancelRequests) {
        if (cancelRequests != null &&! cancelRequests.isEmpty ()) {
            return productService.cancelReserve(cancelRequests);
        } else {
            log.error("Product not found");
            throw new ProductNotExistException("Product not found");
        }
    }

    @PostMapping ("bulk")
    public String addPeople (@RequestBody List <Persona> personas) {
        if (people! = Null &&! People.isEmpty ()) {
            peopleService.insertAll (personas);
            return String.format ("Añadidas% d personas.", people.size ());
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

    @RequestMapping(path = "/product/bulk", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postProductBulk (@RequestBody List<Product> bulkProductRequests){
        if (bulkProductRequests != null && !bulkProductRequests.isEmpty() /*&& !bulkProductRequests.stream().forEach(::getSku)*/){
            productService.postProductBulk(bulkProductRequests);
            return String.format("Added %d product.", bulkProductRequests.size());
        }else {
            return REQUEST_NO_BODY;
        }
    }
}
