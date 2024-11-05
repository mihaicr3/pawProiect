package com.example.barping.entities;

import com.example.barping.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String username;
    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

    // Getters and setters omitted for brevity
}
