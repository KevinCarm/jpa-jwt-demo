package com.kev.jpa.jwt.entities.implementation;

import com.kev.jpa.jwt.entities.model.Product;
import com.kev.jpa.jwt.entities.repository.ProductRepository;
import com.kev.jpa.jwt.entities.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImplementation implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public Product getOneById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product insert(Product product) {
        return repository.save(product);
    }
}
