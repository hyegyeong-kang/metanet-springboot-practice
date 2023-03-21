package com.example.restservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    @Size(min = 2, message = "Name 은 2글자 이상 입력하세요")
    private String name;
    private Date joinDate;

}
