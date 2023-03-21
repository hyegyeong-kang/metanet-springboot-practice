package com.example.restservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "kosa", new Date()));
        users.add(new User(2, "metanet", new Date()));
        users.add(new User(3, "naver", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        for(User user : users) {
            if(user.getId() == id) {  // unboxing 이 자동으로 이루어지기 때문에 int Integer 비교가 가능한 것임.
                return user;
            }
        }
        return null;
    }

    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }
}
