package com.marketplace.product.service.impl;


import com.marketplace.product.controller.request.ProductRequest;

import com.marketplace.product.domain.mapper.ProductMapper;
import com.marketplace.product.domain.model.Product;

import com.marketplace.product.exception.ProductNotExistException;
import com.marketplace.product.repositories.ProductRepository;
import com.marketplace.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;

import com.marketplace.product.domain.model.Product;

import com.marketplace.product.domain.mapper.ProductMapper;
import com.marketplace.product.exception.ProductExistException;
import com.marketplace.product.repositories.ProductRepository;
import com.marketplace.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;


import com.marketplace.product.repositories.ProductRepository;
import com.marketplace.product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class ImplProductService implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        //Iterable to List
        List<Product> products= StreamSupport
                .stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return products;
    }

    @Override
    public Product getProducts(List<String> keywords) {
        return null;
    }

    @Override
    public Product CreateProduct(ProductRequest request) {
        Product product = productMapper.apply(request);
        if (request.getProductId() != null && productRepository.
                findIdProduct(request.getProductId()) != null){
            log.error("El producto ya exite.");
            throw new ProductExistException("El album ya existe.");
        }else{
            productRepository.save(productMapper.apply(request));
        }
        return product;
    }

    @Override
    public Product updateProduct(String sku) {
        return null;
    }

    @Override
    public Product getProductSku(String sku) {
        return null;
    }

    @Override
    public Product putProductSku(ProductRequest request, String sku) {
        Product artist = null;
        if (productRepository.findBySkuProduct(sku) != null) {
            artist = productMapper.apply(request);
            productRepository.save(artist);
        } else {
            log.error("El producto NO existe");
            throw new ProductNotExistException("El producto NO existe");
        }
        return artist;
    }

    @Override
    public Product deleteProduct(String sku) {
        productRepository.deleteBySkuProduct(sku);
        return null;
    }

    @Override
    public Product postProductBulk() {
        return null;
    }
}
