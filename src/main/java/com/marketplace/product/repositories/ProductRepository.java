package com.marketplace.product.repositories;
import com.marketplace.product.domain.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

<<<<<<< HEAD
    List<Product>findByName(String name);

    Product findById(long productId);


    Product deleteBySku(String sku);
=======
    Product findIdProduct(long productId);

    Product deleteBySkuProduct(String sku);
>>>>>>> f39efc36c344f30d978687af7b37b35ddf5860ad

    Product findBySku(String sku);

}
