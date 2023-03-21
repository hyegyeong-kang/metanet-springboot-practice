package com.example.restservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "kosa", new Date(), "pass1", "1234"));
        users.add(new User(2, "metanet", new Date(), "pass2", "5678"));
        users.add(new User(3, "naver", new Date(), "pass3", "9086"));
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

    public User deleteById(int id) {

        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();

            if(user.getId() == id) {
                iterator.remove(); // 참조해서 가져왔기 때문에 리스트에도 삭제가 됨
                return user;
            }
        }
        return null;
    }
}
