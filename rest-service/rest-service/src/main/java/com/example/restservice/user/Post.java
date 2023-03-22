package com.example.restservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    private String description; // 한개의 글이 출력됐으면 좋겠다.

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore // 데이터 불러오지만 화면에 보이지 않도록
    private User user;

}
