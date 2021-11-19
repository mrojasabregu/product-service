package com.marketplace.product.repositories;
import com.marketplace.product.domain.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    List<Product> findByName(String name);

    @Modifying
    @Query(value = "DELETE FROM PRODUCT WHERE SKU = ?1", nativeQuery = true)
    Product deleteBySku(String sku);

    Product findBySku(String sku);

}
