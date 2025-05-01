package com.example.KomponentIntegrationsTestExempel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService1{

    @Autowired
    private UserRepository1 repo;

    public User createUser(String name, String email) {
        if (repo.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }
        return repo.save(new User(null, name, email));
    }

}
