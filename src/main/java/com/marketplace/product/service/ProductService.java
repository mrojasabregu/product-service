package com.marketplace.product.service;
import com.marketplace.product.controller.request.ProductRequest;
import com.marketplace.product.controller.request.PutProductSkuRequest;
import com.marketplace.product.controller.request.ReserveProductRequest;
import com.marketplace.product.domain.model.Product;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductService {

    List<Product> getProducts();

    Product cancelReserve(ProductRequest productRequest, String sku);

    Product getProducts(List<String> keywords);

    Product createProduct(ProductRequest productRequest);

    List<Product> reserveProduct(ReserveProductRequest productRequest, String sku);

    Product getProductSku(String sku);

    Product putProductSku(PutProductSkuRequest request, String sku);

    Product deleteProduct(String sku);

    Product postProductBulk();
}
