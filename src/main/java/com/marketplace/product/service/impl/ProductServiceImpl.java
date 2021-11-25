package com.marketplace.product.service.impl;

import com.marketplace.product.controller.request.*;
import com.marketplace.product.domain.mapper.*;
import com.marketplace.product.controller.request.PutProductSkuRequest;
import com.marketplace.product.controller.request.ReserveProductRequest;
import com.marketplace.product.domain.mapper.PutProductSkuMapper;
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.exception.InventoryNotNegativeException;
import com.marketplace.product.repositories.ProductRepository;
import com.marketplace.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
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
    private BulkProductMapper bulkProductMapper;

    @Override
    public List<Product> getProducts() {
        //Iterable to List
        List<Product> products = StreamSupport
                .stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return products;
    }

    @Override
    public ResponseEntity<Set<Product>> getKeywords(List<String> keywords) {

        return ResponseEntity.ok(productRepository.findByKeywords(keywords));
    }

    @Override
    public void cancelReserve(List<CancelReserveProductRequest> cancelRequests) {

        List<Product> listProductSku = new ArrayList<>();
        cancelRequests.forEach((product) -> {
                    if (productRepository.findBySku(product.getSku()) != null) {
                        Product productSku = productRepository.findBySku(product.getSku());
                        Integer actually = productSku.getUnitAvailable();
                        Integer cancel = product.getAmountToCancel();

                        productSku.setUnitAvailable(actually + cancel);

                        listProductSku.add(productSku);
                    } else {
                        log.info("next sku not found: " + product.getSku());
                    }
                }
        );
        productRepository.saveAll(listProductSku);
        log.info(String.format("canceled %d product.", listProductSku.size()));
    }

    @Override
    public ResponseEntity<List<Product>> reserveProduct(ReserveProductRequest reserveProductRequest, String sku) {

        Product productSku = productRepository.findBySku(sku);
        Integer actually = productSku.getUnitAvailable();
        Integer reserve = reserveProductRequest.getAmountToReserve();

        productSku.setUnitAvailable(actually - reserve);

        if ((actually - reserve) > 0) {
            productSku.setUnitAvailable(actually - reserve);
            return ResponseEntity.ok(Collections.singletonList(productRepository.save(productSku)));
        } else {
            log.error("No units available");
            log.info("There are " + actually + " units available");
            throw new InventoryNotNegativeException("No units available");
        }
    }

    @Override
    public ResponseEntity<Product> createProduct(PostProductRequest postProductRequest) {

        Product product = postProductMapper.apply(postProductRequest);
        return ResponseEntity.ok(productRepository.save(product));
    }

    @Override
    public Product getProductSku(String sku) {
        return productRepository.findBySku(sku);
    }

    @Override
    public ResponseEntity<Product> putProductSku(PutProductSkuRequest request, String sku) {
        Product productSku = productRepository.findBySku(sku);
        Product product = putProductSkuMapper.apply(request);
        product.setProductId(productSku.getProductId());
        product.setKeywords(productSku.getKeywords());

        return ResponseEntity.ok(productRepository.save(product));
    }

    @Override
    public ResponseEntity<Product> deleteProduct(String sku) {
        Product deleted = productRepository.findBySku(sku);
        productRepository.delete(deleted);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @Override
    public void postProductBulk(List<BulkProductRequest> bulkProductRequests) {
        List<Product> productsList = new ArrayList<>();

        if (bulkProductRequests != null && !bulkProductRequests.isEmpty()) {
            bulkProductRequests.forEach((product) -> {
                if (productRepository.findBySku(product.getSku()) != null) {
                    log.info("next, existing sku" + product.getSku());
                } else {

                    productsList.add(bulkProductMapper.apply(product));
                }
            });
            productRepository.saveAll(productsList);
            log.info(String.format("added %d product.", productsList.size()));
        }

    }
}