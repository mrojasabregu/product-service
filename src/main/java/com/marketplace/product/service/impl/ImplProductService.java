package com.marketplace.product.service.impl;


import com.marketplace.product.domain.model.Product;

import com.marketplace.product.repositories.ProductRepository;
import com.marketplace.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplProductService implements ProductService {

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
    public Product CreateProduct() {
        return null;
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
        productRepository.deleteBySkuProduct(sku);
        return null;
    }

    @Override
    public Product postProductBulk() {
        return null;
    }
}
