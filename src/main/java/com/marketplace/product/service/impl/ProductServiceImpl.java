package com.marketplace.product.service.impl;

<<<<<<< HEAD
import com.marketplace.product.controller.request.*;
import com.marketplace.product.domain.mapper.*;
import com.marketplace.product.domain.model.Keyword;
=======
import com.marketplace.product.controller.request.ProductRequest;
import com.marketplace.product.controller.request.PutProductSkuRequest;
import com.marketplace.product.controller.request.ReserveProductRequest;
import com.marketplace.product.domain.mapper.ProductMapper;
import com.marketplace.product.domain.mapper.PutProductSkuMapper;
import com.marketplace.product.domain.mapper.ReserveProductMapper;
>>>>>>> e294a692483dd88f1a730afac80d1a6e2f95fc6f
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.exception.ProductNotExistException;
import com.marketplace.product.repositories.ProductRepository;
import com.marketplace.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private PostProductSkuMapper postProductSkuMapper;

    @Autowired
    private CancelReserveProductMapper cancelReserveProductMapper;

    @Override
    public ResponseEntity<List<Product>> getProducts() {
        //Iterable to List
        List<Product> products = StreamSupport
                .stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<Product> cancelReserve(CancelReserveProductRequest cancelReserveProductRequest, String sku) {

        if (cancelReserveProductRequest.getSku() != null) {
            Product request = cancelReserveProductMapper.apply(cancelReserveProductRequest);
            Product productSku = productRepository.findBySku(sku);

            Integer actually = productSku.getUnitAvailable();
            Integer cancel = request.getAmountToCancel();

            productSku.setUnitAvailable(actually + cancel);

            return ResponseEntity.ok(productRepository.save(productSku));
        } else{
            log.error("Product not found");
            throw new ProductNotExistException("Product not found");
        }

    }

    @Override
    public ResponseEntity<Product> getProducts(List<String> keywords) {
        return null;
    }

    @Override
    public ResponseEntity<Product> createProduct(PostProductSkuRequest postProductSkuRequest) {
        Product product = postProductSkuMapper.apply(postProductSkuRequest);

        return ResponseEntity.ok(productRepository.save(product));
    }

    @Override
    public ResponseEntity<List<Product>> reserveProduct(ReserveProductRequest reserveProductRequest, String sku) {
        if (reserveProductRequest.getSku() != null) {
            Product request = reserveProductMapper.apply(reserveProductRequest);
            Product productSku = productRepository.findBySku(sku);

            Integer actually = productSku.getUnitAvailable();
            Integer reserve = request.getAmountToReserve();

            productSku.setUnitAvailable(actually - reserve);

            return ResponseEntity.ok(Arrays.asList(productRepository.save(productSku)));
        } else{
            log.error("Product not found");
            throw new ProductNotExistException("Product not found");
        }
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
