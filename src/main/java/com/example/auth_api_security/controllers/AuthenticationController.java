package com.example.auth_api_security.controllers;

import com.example.auth_api_security.domain.User;
import com.example.auth_api_security.dtos.user.AuthenticationDTO;
import com.example.auth_api_security.dtos.user.RegisterDTO;
import com.example.auth_api_security.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService service;


    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){

        if (service.loadUserByUsername(data.login()) == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.loginUser(data));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterDTO registerData){

        if (service.loadUserByUsername(registerData.login()) != null) return ResponseEntity.badRequest().build();

        service.registerUser(registerData);
        return ResponseEntity.ok().build();
    }
}
