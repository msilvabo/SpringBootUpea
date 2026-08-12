package com.postgrado.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
