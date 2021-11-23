package com.marketplace.product.service;
import com.marketplace.product.controller.request.*;
import com.marketplace.product.domain.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Repository
public interface ProductService {

    ResponseEntity<Set<Product>> getKeywords(List<String> keywords);

    ResponseEntity<Product> cancelReserve(CancelReserveProductRequest cancelReserveProductRequest, String sku);

    ResponseEntity<List<Product>> getProducts();

    ResponseEntity<Product> createProduct(PostProductRequest postProductRequest);

    ResponseEntity<List<Product>> reserveProduct(ReserveProductRequest productRequest, String sku);

    ResponseEntity<Product> getProductSku(String sku);

    ResponseEntity<Product> putProductSku(PutProductSkuRequest request, String sku);

    ResponseEntity<Product> deleteProduct(String sku);

    List<Product> postProductBulk(List<Product> p);




}
