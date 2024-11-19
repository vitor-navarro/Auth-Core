package com.login.system.auth.core.rest.controllers;

import com.login.system.auth.core.dto.UserDTO;
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
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
        UserDTO user = userService.getUser(id);
        return user != null ? ResponseEntity.ok(user):ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "", produces = "application/json")
    public List<UserDTO> geAllUsers(){
        return userService.getAllUsers();
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