package com.webstreaming.webstreaming.repositories;

import com.webstreaming.webstreaming.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByEmail(String email);
//    Optional<Customer> findByUsername(String username);
    boolean existsByEmail(String email);
}
