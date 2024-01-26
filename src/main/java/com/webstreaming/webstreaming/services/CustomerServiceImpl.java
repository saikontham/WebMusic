package com.webstreaming.webstreaming.services;

import com.webstreaming.webstreaming.entities.Customer;
import com.webstreaming.webstreaming.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }

    @Override
    public boolean isEmailUnique(String email) {
        return !customerRepository.existsByEmail(email);

    }
}
