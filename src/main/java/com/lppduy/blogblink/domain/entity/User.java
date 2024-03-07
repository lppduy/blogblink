package com.lppduy.blogblink.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Date createdAt;
    private Date updatedAt;
}
