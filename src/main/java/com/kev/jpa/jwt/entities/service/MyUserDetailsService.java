package com.kev.jpa.jwt.entities.service;

import com.kev.jpa.jwt.entities.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service that a user with the given email is looking for
 **/
@Service
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserService service;

    /**
     * Get the user that matches with the email provided
     *
     * @param email email provided by the user in the login
     * @return a new object {@link org.springframework.security.core.userdetails.User} with the email, password and roles
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = service.getUserByEmail(email);
        if (user != null) {
            List<GrantedAuthority> roles;
            roles = user.getRoles()
                    .stream()
                    .map(item -> new SimpleGrantedAuthority(item.getName()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    roles
            );
        }
        return null;
    }
}
