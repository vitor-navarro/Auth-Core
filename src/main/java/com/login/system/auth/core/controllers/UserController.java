package com.login.system.auth.core.controllers;

import com.login.system.auth.core.dto.UserCreateDTO;
import com.login.system.auth.core.dto.UserDTO;
import com.login.system.auth.core.entity.UserEntity;
import com.login.system.auth.core.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public UserDTO getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "", produces = "application/json")
    public List<UserDTO> geAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity createUser(@Valid @RequestBody UserCreateDTO user) {
        return userService.createUser(user);
    }

    @PutMapping(path = "/update")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@Valid @RequestBody UserDTO user){
        return userService.updateUser(user);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Void> deleteUserById(@RequestBody Map<String, Integer> payload){
        Integer id = payload.get("id");
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}