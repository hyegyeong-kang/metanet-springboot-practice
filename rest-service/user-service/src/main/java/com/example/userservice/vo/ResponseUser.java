package com.example.userservice.vo;

import lombok.Data;

@Data
public class ResponseUser {  // 클라이언트가 볼 수 있는 내용만 객체로 만들어서 보내주는 것
    private String email;
    private String name;
    private String userId;
}
