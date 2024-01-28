package com.webstreaming.webstreaming.services;

import com.webstreaming.webstreaming.entities.Customer;

import java.util.Optional;

public interface CustomerService {

    public void registerCustomer(Customer customer);

    public boolean isEmailUnique(String email);
    public Customer getCustomer(Integer id);
    public Optional<Customer> getCustomerbyemail(String email);
    public void updateCustomer(Customer customer);
    public Customer findCustomerByEmail(String email);
}
