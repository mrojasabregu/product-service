package com.marketplace.product.service;
import com.marketplace.product.controller.request.KeywordRequest;
import com.marketplace.product.controller.request.ProductRequest;
import com.marketplace.product.domain.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductService {

    List<Product> getProducts();

    Product cancelReserve(ProductRequest productRequest, String sku);

    Product getProducts(List<String> keywords);

    Product CreateProduct(ProductRequest productRequest);

    Product updateProduct(ProductRequest productRequest, String sku);

    Product getProductSku(String sku);

    Product putProductSku(ProductRequest request, String sku);

    Product deleteProduct(String sku);

    Product postProductBulk();
}
