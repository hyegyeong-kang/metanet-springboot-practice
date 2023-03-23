package com.example.userservice.vo;

import lombok.Data;

@Data
public class RequestUser { // 회원가입할 때 입력할 데이터만
    private String email;
    private String name;
    private String pwd; // 나중에 암호화 됨
}
