package com.kev.jpa.jwt.entities.repository;

import com.kev.jpa.jwt.entities.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT u FROM User u WHERE email = :email")
    User getUserByEmail(@Param("email") String email);
}
