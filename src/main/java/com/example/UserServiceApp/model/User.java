package com.example.UserServiceApp.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table (name = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String role;
    private String username;
}
