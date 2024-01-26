package com.webstreaming.webstreaming.services;

import com.webstreaming.webstreaming.entities.Customer;

public interface CustomerService {

    public void registerCustomer(Customer customer);

    public boolean isEmailUnique(String email);
}
