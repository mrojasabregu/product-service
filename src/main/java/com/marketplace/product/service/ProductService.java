package com.marketplace.product.service;
import com.marketplace.product.controller.request.ProductRequest;
import com.marketplace.product.controller.request.PutProductSkuRequest;
import com.marketplace.product.controller.request.ReserveProductRequest;
import com.marketplace.product.domain.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductService {

    ResponseEntity<List<Product>> getProducts();

    ResponseEntity<Product> cancelReserve(ProductRequest productRequest, String sku);

    ResponseEntity<Product> getProducts(List<String> keywords);

    ResponseEntity<Product> createProduct(ProductRequest productRequest);

    ResponseEntity<List<Product>> reserveProduct(ReserveProductRequest productRequest, String sku);

    ResponseEntity<Product> getProductSku(String sku);

    ResponseEntity<Product> putProductSku(PutProductSkuRequest request, String sku);

    ResponseEntity<Product> deleteProduct(String sku);

    ResponseEntity<Product> postProductBulk();
}
