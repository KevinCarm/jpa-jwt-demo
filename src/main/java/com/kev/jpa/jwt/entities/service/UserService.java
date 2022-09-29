package com.kev.jpa.jwt.entities.service;

import com.kev.jpa.jwt.entities.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUserByEmail(String email);
    User getOneById(Long id);
}
