package com.marketplace.product.service.impl;

import com.marketplace.product.controller.request.KeywordRequest;
import com.marketplace.product.controller.request.ProductRequest;
import com.marketplace.product.controller.request.PutProductSkuRequest;
import com.marketplace.product.controller.request.ReserveProductRequest;
import com.marketplace.product.domain.mapper.KeywordMapper;
import com.marketplace.product.domain.mapper.ProductMapper;
import com.marketplace.product.domain.mapper.PutProductSkuMapper;
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
    private PutProductSkuMapper putProductSkuMapper;

    @Autowired
    private ReserveProductMapper reserveProductMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<Product>> getProducts() {
        //Iterable to List
        List<Product> products = StreamSupport
                .stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<Product> cancelReserve(ProductRequest productRequest, String sku) {
        Product request = productMapper.apply(productRequest);
        Product productSku = productRepository.findBySku(sku);

        if (productRequest.getSku() != null) {
            Integer actually = productSku.getUnitAvailable();
            Integer cancel = request.getAmountToCancel();

            productSku.setUnitAvailable(actually + cancel);

            return ResponseEntity.ok(productRepository.save(productSku));
        } else
            log.error("Product not found");
        throw new ProductNotExistException("Product not found");
    }

    @Override
    public ResponseEntity<Product> getProducts(List<String> keywords) {
        return null;
    }

    @Override
    public ResponseEntity<Product> createProduct(ProductRequest productRequest) {
        Product product = productMapper.apply(productRequest);

        if (productRequest.getProductId() != null) {
            log.error("Product already exists");
            throw new ProductExistException("Product already exists");
        } else {
            return ResponseEntity.ok(productRepository.save(product));
        }
    }

    @Override
    public ResponseEntity<List<Product>> reserveProduct(ReserveProductRequest reserveProductRequest, String sku) {
        List<Product> listReserveProduct=new ArrayList<>();
        Product request = reserveProductMapper.apply(reserveProductRequest);
        Product productSku = productRepository.findBySku(sku);

        Integer actually = productSku.getUnitAvailable();
        Integer reserve = request.getAmountToReserve();

        productSku.setUnitAvailable(actually - reserve);

        listReserveProduct.add(productRepository.save(productSku));

        return ResponseEntity.ok(listReserveProduct);
    }

    @Override
    public ResponseEntity<Product> getProductSku(String sku) {
        return ResponseEntity.ok(productRepository.findBySku(sku));
    }

    @Override
    public ResponseEntity<Product> putProductSku(PutProductSkuRequest request, String sku) {
        Product product;
        if (productRepository.findBySku(sku) != null) {
            product = putProductSkuMapper.apply(request);
            return ResponseEntity.ok(productRepository.save(product));
        } else {
            log.error("The product does NOT exist");
            throw new ProductNotExistException("The product does NOT exist");
        }
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
