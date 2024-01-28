package com.webstreaming.webstreaming.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customers")
@Setter
@Getter
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
    @JsonIgnore
    @OneToMany(mappedBy = "customer")

    private Set<LikedSong> likedSong;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)

    @JoinTable(
            name = "customer_playlist",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id")
    )
    private List<Playlist> playlists;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", likedSong=" + likedSong +

                '}';
    }
}
