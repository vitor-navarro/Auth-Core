package com.login.system.auth.core.service;

import com.login.system.auth.core.dto.LoginDTO;
import com.login.system.auth.core.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    //TODO adicionar password encoder
    public boolean authenticate(LoginDTO loginDTO){

        UserDTO user = userService.getUserByUsername(loginDTO.getUsername());

        if(Objects.equals(loginDTO.getUsername(), user.getUsername())){
            return userService.validatePassword(loginDTO.getUsername(), loginDTO.getPassword());
        }

        return false;
    }
}
