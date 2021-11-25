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

    void cancelReserve(List<CancelReserveProductRequest> cancelReserveProductRequest);

    List<Product> getProducts();

    ResponseEntity<Product> createProduct(PostProductRequest postProductRequest);

    ResponseEntity<List<Product>> reserveProduct(ReserveProductRequest productRequest, String sku);

    Product getProductSku(String sku);

    ResponseEntity<Product> putProductSku(PutProductSkuRequest request, String sku);

    ResponseEntity<Product> deleteProduct(String sku);

    void postProductBulk(List<BulkProductRequest> bulkProductRequests);




}
