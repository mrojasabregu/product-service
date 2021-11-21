package com.marketplace.product.service.impl;

import com.marketplace.product.controller.request.KeywordRequest;
import com.marketplace.product.controller.request.ProductRequest;
import com.marketplace.product.controller.request.ReserveProductRequest;
import com.marketplace.product.domain.mapper.KeywordMapper;
import com.marketplace.product.domain.mapper.ProductMapper;
import com.marketplace.product.domain.mapper.ReserveProductMapper;
import com.marketplace.product.domain.model.Keyword;
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.exception.ProductNotExistException;
import com.marketplace.product.repositories.KeywordRepository;
import com.marketplace.product.repositories.ProductRepository;
import com.marketplace.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import com.marketplace.product.exception.ProductExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ReserveProductMapper reserveProductMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        //Iterable to List
        List<Product> products = StreamSupport
                .stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return products;
    }

    @Override
    public Product cancelReserve(ProductRequest productRequest, String sku) {
        Product request = productMapper.apply(productRequest);
        Product productSku = productRepository.findBySku(sku);

        if (sku != null) {
            Integer actually = productSku.getUnitAvailable();
            Integer cancel = request.getAmountToCancel();

            productSku.setUnitAvailable(actually + cancel);

            return productRepository.save(productSku);
        } else
            log.error("Product not found");
        throw new ProductNotExistException("Product not found");
    }

    @Override
    public Product getProducts(List<String> keywords) {
        return null;
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        Product product = productMapper.apply(productRequest);

        if (productRequest.getProductId() != null) {
            log.error("Product already exists");
            throw new ProductExistException("Product already exists");
        } else {
            return productRepository.save(product);
        }
    }

    @Override
    public Product reserveProduct(ReserveProductRequest reserveProductRequest, String sku) {
        Product request = reserveProductMapper.apply(reserveProductRequest);
        Product productSku = productRepository.findBySku(sku);

        Integer actually = productSku.getUnitAvailable();
        Integer reserve = request.getAmountToReserve();

        productSku.setUnitAvailable(actually - reserve);

        return productRepository.save(productSku);

    }

    @Override
    public Product getProductSku(String sku) {
        return productRepository.findBySku(sku);
    }

    @Override
    public Product putProductSku(ProductRequest request, String sku) {
        Product product;
        if (productRepository.findBySku(sku) != null) {
            product = productMapper.apply(request);
            //productRepository.delete(productRepository.findBySku(request.getSku()));
            return productRepository.save(product);
        } else {
            log.error("The product does NOT exist");
            throw new ProductNotExistException("The product does NOT exist");
        }
    }


    @Override
    public Product deleteProduct(String sku) {
        Product deleted = productRepository.findBySku(sku);
        productRepository.delete(deleted);
        return null;
    }

    @Override
    public Product postProductBulk() {
        return null;
    }
}
