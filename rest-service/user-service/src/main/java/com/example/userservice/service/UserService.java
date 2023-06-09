package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.jpa.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Iterator;

public interface UserService extends UserDetailsService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();
    UserDTO getUserDetailByEmail(String userName);

}
