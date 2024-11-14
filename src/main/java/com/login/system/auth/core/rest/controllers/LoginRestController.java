package com.login.system.auth.core.rest.controllers;

import com.login.system.auth.core.dto.LoginDTO;
import com.login.system.auth.core.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/login")
public class LoginRestController {

    @Autowired
    private AuthService authService;

    /*@PostMapping() //TODO adicionar password encoder
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        String token = authService.generateTokenIfAuthenticated(loginDTO);

        if (token != null) {
            return ResponseEntity.ok().body(Map.of("Logged, token:", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }*/



}
