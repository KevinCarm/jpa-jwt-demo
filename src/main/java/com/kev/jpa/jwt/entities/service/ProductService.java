package com.kev.jpa.jwt.entities.service;

import com.kev.jpa.jwt.entities.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getOneById(Long id);
    Product insert(Product product);
}
