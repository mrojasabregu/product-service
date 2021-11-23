package com.marketplace.product.service.impl;
import com.marketplace.product.controller.request.*;
import com.marketplace.product.domain.mapper.*;

import com.marketplace.product.controller.request.PutProductSkuRequest;
import com.marketplace.product.controller.request.ReserveProductRequest;
import com.marketplace.product.domain.mapper.PutProductSkuMapper;
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.repositories.KeywordRepository;
import com.marketplace.product.repositories.ProductRepository;
import com.marketplace.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private PutProductSkuMapper putProductSkuMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PostProductMapper postProductMapper;


    @Autowired
    private KeywordRepository keywordRepository;

    public ResponseEntity<List<Product>> getProducts() {
        //Iterable to List
        List<Product> products = StreamSupport
                .stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<Set<Product>> getKeywords(List<String> keywords) {

        return ResponseEntity.ok(productRepository.findByKeywords(keywords));
    }

    @Override
    public ResponseEntity<Product> cancelReserve(CancelReserveProductRequest cancelReserveProductRequest, String sku) {

        Product productSku = productRepository.findBySku(sku);

        Integer actually = productSku.getUnitAvailable();
        Integer cancel = cancelReserveProductRequest.getAmountToCancel();

        productSku.setUnitAvailable(actually + cancel);

        return ResponseEntity.ok(productRepository.save(productSku));
    }

    @Override
    public ResponseEntity<List<Product>> reserveProduct(ReserveProductRequest reserveProductRequest, String sku) {

        Product productSku = productRepository.findBySku(sku);

        Integer actually = productSku.getUnitAvailable();
        Integer cancel = reserveProductRequest.getAmountToReserve();

        productSku.setUnitAvailable(actually - cancel);

        return ResponseEntity.ok(Collections.singletonList(productRepository.save(productSku)));
    }

    @Override
    public ResponseEntity<Product> createProduct(PostProductRequest postProductRequest) {

        Product product = postProductMapper.apply(postProductRequest);
        return ResponseEntity.ok(productRepository.save(product));
    }

    @Override
    public ResponseEntity<Product> getProductSku(String sku) {
        return ResponseEntity.ok(productRepository.findBySku(sku));
    }

    @Override
    public ResponseEntity<Product> putProductSku(PutProductSkuRequest request, String sku) {
        Product product;
        product = putProductSkuMapper.apply(request);
        return ResponseEntity.ok(productRepository.save(product));
    }

    @Override
    public ResponseEntity<Product> deleteProduct(String sku) {
        Product deleted = productRepository.findBySku(sku);
        productRepository.delete(deleted);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Product> postProductBulk() {
        return null;
    }

}
