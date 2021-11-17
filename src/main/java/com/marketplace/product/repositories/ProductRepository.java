package com.marketplace.product.repositories;

import com.marketplace.product.domain.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    Product deleteBySkuProduct(String sku);

    Product findBySkuProduct(String sku);
}
