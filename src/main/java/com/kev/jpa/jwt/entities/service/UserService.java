package com.kev.jpa.jwt.entities.service;

import com.kev.jpa.jwt.entities.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUserByEmail();
    User getOneById(Long id);
}
