package com.kev.jpa.jwt.entities.repository;

import com.kev.jpa.jwt.entities.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
