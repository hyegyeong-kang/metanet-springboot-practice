package com.example.userservice.vo;

import lombok.Data;

@Data
public class RequestLogin { // 로그인할 때 필요한 정보만 받는 것 ( 두개의 정보만 전달하겠다)
    private String email;
    private String password;
}
