package com.example.homework1.repository;

import com.example.homework1.authorities.Authorities;
import com.example.homework1.model.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private final Map<User, List<Authorities>> map = new ConcurrentHashMap<>();

    public UserRepository() {
        addUserAuthorities(new User("admin", "admin"), Arrays.asList(Authorities.READ, Authorities.WRITE, Authorities.DELETE));
    }

    public List<Authorities> getUserAuthorities(User user) {
        return map.get(user);
    }

    public void addUserAuthorities(User user, List<Authorities> list) {
        map.put(user, list);
    }
}
