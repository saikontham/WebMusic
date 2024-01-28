package com.webstreaming.webstreaming.services;

import com.webstreaming.webstreaming.entities.Customer;
import com.webstreaming.webstreaming.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Customer getCustomer(Integer id) {
        Customer byEmail = customerRepository.findCustomerById(id);
        return byEmail;
    }

    @Override
    public Optional<Customer> getCustomerbyemail(String email) {
        return customerRepository.findByEmail(email);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
}
