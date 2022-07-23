package com.example.homework1.controller;

import com.example.homework1.authorities.Authorities;
import com.example.homework1.exception.UnauthorizedUser;
import com.example.homework1.model.User;
import com.example.homework1.service.AuthorizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AuthorizationController {

    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@Valid User user) {
        return service.getAuthorities(user);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> unauthorizedUser(UnauthorizedUser unauthorizedUser) {
        System.out.println(unauthorizedUser.getMessage());
        return new ResponseEntity<>(unauthorizedUser.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
