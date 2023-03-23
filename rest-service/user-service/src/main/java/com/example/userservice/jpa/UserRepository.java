package com.example.userservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId); // 이걸 하나의 테이블로 봐야 함
    UserEntity findByEmail(String username);

}
