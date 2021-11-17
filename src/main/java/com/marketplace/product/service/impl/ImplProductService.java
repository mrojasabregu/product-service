package com.marketplace.product.service.impl;


import com.marketplace.product.controller.request.ProductRequest;
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.domain.mapper.ProductMapper;
import com.marketplace.product.exception.ProductExistException;
import com.marketplace.product.repositories.ProductRepository;
import com.marketplace.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ImplProductService implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProducts() {
        return null;
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
    public Product putProductSku(String sku) {
        return null;
    }

    @Override
    public Product deleteProduct(String sku) {
        return null;
    }

    @Override
    public Product postProductBulk() {
        return null;
    }
}
