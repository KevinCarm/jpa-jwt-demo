package com.kev.jpa.jwt.controllers;

import com.kev.jpa.jwt.entities.model.Product;
import com.kev.jpa.jwt.entities.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id) {
        Product product = service.getOneById(id);
        if(product == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("code", HttpStatus.NOT_FOUND);
            error.put("message", "Product not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/")
    public ResponseEntity<?> insert(@RequestBody Product product) {
        if(product == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("code", HttpStatus.BAD_REQUEST);
            error.put("message", "Bad request product format");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(product);
    }
}
