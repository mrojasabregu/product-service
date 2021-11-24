package com.marketplace.product.repositories;

import com.marketplace.product.domain.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByName(String name);

    Product findBySku(String sku);

    @Query(value = "select p.product_id, p.sku, p.name, p.description, p.price, p.img_url, p.unit_available, p.weight, p.category, p.brand, p.amount_to_cancel, p.amount_to_reserve from keyword_name kn inner join product_keyword pk on kn.keyword_keyword_id = pk.keyword_id  inner join product p on pk.product_id = p.product_id WHERE kn.name IN :keywords", nativeQuery = true)
    Set<Product> findByKeywords(@Param("keywords") List<String> keywords);


}
