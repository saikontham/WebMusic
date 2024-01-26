package com.webstreaming.webstreaming.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "UserName is required")
    @Size(min = 3,max = 50,message = "UserName must be between 3 and 50 characters")
    private String username;

    @Column(nullable = false)

    private String password;

    @NotBlank(message = "email is required")
    @Email(message = "Invalid email format")
    private String email;
    private String role;
}
