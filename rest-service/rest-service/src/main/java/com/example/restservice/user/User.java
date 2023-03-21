package com.example.restservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"password", "ssn"}) // 두개를 다 가리겠다.(동시에 작성가능)
//@JsonFilter("UserInfo") // 이 객체를 UserInfo 라는 이름으로 컨트롤러에서 처리하겠다.
// 컨트롤러에 따라서 권한이 다르기 때문에 따로따로 쓰기 위함임
public class User {
    private Integer id;
    @Size(min = 2, message = "Name 은 2글자 이상 입력하세요")
    private String name;
    private Date joinDate;

   // @JsonIgnore // 데이터 담겼지만 노출은 안시키겠다.
    private String password;
    private String ssn;

}
