package com.example.userservice.jpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users3")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql 에서 자동으로 증가됨
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, unique = true)
    private String encryptedPwd;
}
