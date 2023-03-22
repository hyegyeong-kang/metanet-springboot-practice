package com.example.restservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"password", "ssn"}) // 두개를 다 가리겠다.(동시에 작성가능)
//@JsonFilter("UserInfo") // 이 객체를 UserInfo 라는 이름으로 컨트롤러에서 처리하겠다.
// 컨트롤러에 따라서 권한이 다르기 때문에 따로따로 쓰기 위함임
@Entity
public class User {
    @Id // PK 설정
    @GeneratedValue // 1씩 증가시켜주는 것
    private Integer id;
    @Size(min = 2, message = "Name 은 2글자 이상 입력하세요")
    private String name;
    private Date joinDate;

   // @JsonIgnore // 데이터 담겼지만 노출은 안시키겠다.
    private String password;
    private String ssn;

    @OneToMany(mappedBy = "user") // 변수명 post.java
    private List<Post> posts; // 1:N 이기 때문에 List 처리

    public User(Integer id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
