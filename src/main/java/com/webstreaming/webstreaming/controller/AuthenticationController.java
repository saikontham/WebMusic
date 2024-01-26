package com.webstreaming.webstreaming.controller;

import com.webstreaming.webstreaming.entities.Customer;
import com.webstreaming.webstreaming.entities.LoginRequest;
import com.webstreaming.webstreaming.services.CustomerService;
import com.webstreaming.webstreaming.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/")
    public String get2()
    {
        return "register";
    }
    @GetMapping("/logged")
    @PreAuthorize("hasAuthority('user')")
    public String get()
    {
        return "logged user";
    }
    @GetMapping("/loggedin")
    @PreAuthorize("hasAuthority('admin')")
    public String get1()
    {
        return "logged admin";
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) throws Exception {
        try {
            if (customer != null) {
                // Check if the email is already registered
                if (customerService.isEmailUnique(customer.getEmail())) {
                    customerService.registerCustomer(customer);
                    return ResponseEntity.ok().body("Customer Registration Success");
                } else {
                    return ResponseEntity.badRequest().body("Email is already registered");
                }
            } else {
                return ResponseEntity.badRequest().body("Failure of Customer Registration");
            }
        } catch (Exception e) {
            throw new Exception("Error during customer registration: " + e.getMessage());
        }
    }


    //    @PostMapping("/login")
//    public ResponseEntity<String> loginValidate(@RequestBody LoginRequest loginRequest) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//            if (authentication.isAuthenticated()) {
//                String token = jwtService.generateToken(loginRequest.getUsername());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                return ResponseEntity.ok(token);
//            } else {
//                // Authentication failed
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//            }
//        } catch (UsernameNotFoundException e) {
//            // User not found
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        } catch (AuthenticationException e) {
//            // Other authentication-related exceptions
//            System.out.println(e.getMessage());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
//        }
//
//
//
//
//    }
@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(loginRequest.getEmail());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(token);
        } else {
            // Authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    } catch (UsernameNotFoundException e) {
        // User not found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    } catch (AuthenticationException e) {
        // Other authentication-related exceptions
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    }
}


}
