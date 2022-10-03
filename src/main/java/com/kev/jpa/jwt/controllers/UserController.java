package com.kev.jpa.jwt.controllers;

import com.kev.jpa.jwt.entities.model.AuthenticationRequest;
import com.kev.jpa.jwt.entities.model.AuthenticationResponse;
import com.kev.jpa.jwt.entities.model.User;
import com.kev.jpa.jwt.entities.service.UserService;
import com.kev.jpa.jwt.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        service.getAll().get(0).getProducts().forEach(System.out::println);
        return ResponseEntity.ok(service.getAll().stream().toList());
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect email and password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(
                authenticationRequest.getEmail()
        );
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
