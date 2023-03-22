package com.example.restservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// DAO 라고 생각하면 됨
@Repository // 객체 생성
public interface UserRepository extends JpaRepository<User, Integer> { // 테이블명, 주키 타입

}
