package com.login.system.auth.core.rest.controllers;

import com.login.system.auth.core.dto.LoginDTO;
import com.login.system.auth.core.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/login")
public class LoginRestController {

    @Autowired
    private AuthService authService;

    /*@PostMapping() //TODO adicionar password encoder
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        boolean isAuthenticated = authService.authenticate(loginDTO);
        //TODO adicionar e retornar o Token


    }*/



}
