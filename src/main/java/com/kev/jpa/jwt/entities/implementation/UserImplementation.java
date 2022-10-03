package com.kev.jpa.jwt.entities.implementation;

import com.kev.jpa.jwt.entities.model.User;
import com.kev.jpa.jwt.entities.repository.UserRepository;
import com.kev.jpa.jwt.entities.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserImplementation implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User insert(User user){
        return repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return repository.getUserByEmail(email);
    }

    @Override
    public User getOneById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
