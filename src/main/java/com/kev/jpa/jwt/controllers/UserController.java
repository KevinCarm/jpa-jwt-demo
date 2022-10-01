package com.kev.jpa.jwt.controllers;

import com.kev.jpa.jwt.entities.model.User;
import com.kev.jpa.jwt.entities.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id) {
        User user = service.getOneById(id);
        if(user == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("code", HttpStatus.NOT_FOUND);
            error.put("message", "User not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/query")
    public ResponseEntity<?> getByEmail(@RequestParam("email") String email) {
        User user = service.getUserByEmail(email);
        if(user == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("code", HttpStatus.NOT_FOUND);
            error.put("message", "User not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<?> insert(@RequestBody User user) {
        if(user == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("code", HttpStatus.BAD_REQUEST);
            error.put("message", "Bad request user format");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        service.insert(user);
        return ResponseEntity.ok(user);
    }
}
