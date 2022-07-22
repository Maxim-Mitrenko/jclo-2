package com.example.homework1.repository;

import com.example.homework1.authorities.Authorities;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private final Map<String, List<Authorities>> map = new ConcurrentHashMap<>();

    public UserRepository() {
        addUserAuthorities("admin", "admin", Arrays.asList(Authorities.READ, Authorities.WRITE, Authorities.DELETE));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        return map.get(userAndPasswordFormat(user, password));
    }

    public void addUserAuthorities(String user, String password, List<Authorities> list) {
        map.put(userAndPasswordFormat(user, password), list);
    }

    private String userAndPasswordFormat(String user, String password) {
        return String.format("%s %s", user, password);
    }
}
