package com.example.homework1.service;

import com.example.homework1.authorities.Authorities;
import com.example.homework1.exception.InvalidCredentials;
import com.example.homework1.exception.UnauthorizedUser;
import com.example.homework1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationService {

    private final UserRepository repository;

    public AuthorizationService(UserRepository repository) {
        this.repository = repository;
    }

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) throw new InvalidCredentials("User name or password is empty");
        List<Authorities> list = repository.getUserAuthorities(user, password);
        if (isEmpty(list)) throw new UnauthorizedUser("Unknown user " + user);
        return list;
    }

    private boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    private boolean isEmpty(List<Authorities> list) {
        return list == null || list.isEmpty();
    }
}
