package com.marketplace.product.repositories;

import com.marketplace.product.domain.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    List<Product>findByName(String name);

    Product findIdProduct(long productId);


    Product deleteBySkuProduct(String sku);

}
