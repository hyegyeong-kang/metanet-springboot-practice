package com.example.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userId;
    private String name;
    private String pwd;
    private String email;

    private String encryptedPwd;
    private String decryptedPwd;

}
