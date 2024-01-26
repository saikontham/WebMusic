//package com.webstreaming.webstreaming.security;
//
//import com.webstreaming.webstreaming.entities.Customer;
//import com.webstreaming.webstreaming.repositories.CustomerRepository;
//import com.webstreaming.webstreaming.services.CustomerDetailsUserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class CustomerDetailsService implements UserDetailsService {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Customer> userRegistration=customerRepository.findByUsername(username);
//
//        return userRegistration.map(CustomerDetailsUserDetails::new)
//                .orElseThrow(()->new UsernameNotFoundException("User not found "+username));
//
//    }
//}
package com.webstreaming.webstreaming.security;

import com.webstreaming.webstreaming.entities.Customer;
import com.webstreaming.webstreaming.repositories.CustomerRepository;
import com.webstreaming.webstreaming.services.CustomerDetailsUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> userRegistration = customerRepository.findByEmail(email);

        return userRegistration.map(CustomerDetailsUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

}

