package com.marketplace.product.service;


import com.marketplace.product.domain.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductService {

    Product getProducts();

    Product getProducts(List<String> keywords);

    Product CreateProduct();

    Product updateProduct(String sku);

    Product getProductSku(String sku);

    Product putProductSku(String sku);

    Product deleteProduct(String sku);

    Product postProductBulk();
}
