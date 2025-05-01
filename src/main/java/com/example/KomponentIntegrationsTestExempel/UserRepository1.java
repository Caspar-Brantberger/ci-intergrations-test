package com.example.KomponentIntegrationsTestExempel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository1 extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
