package com.marketplace.product.repositories;

import com.marketplace.product.domain.model.Keyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends CrudRepository<Keyword,Long> {
}
