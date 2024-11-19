package com.login.system.auth.core.rest.controllers;

import com.login.system.auth.core.dto.AuthenticationDTO;
import com.login.system.auth.core.dto.AuthenticationResponseDTO;
import com.login.system.auth.core.dto.RegisterDTO;
import com.login.system.auth.core.entity.UserEntity;
import com.login.system.auth.core.infra.security.TokenService;
import com.login.system.auth.core.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate((usernamePassword));

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return ResponseEntity.ok(new AuthenticationResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        // Verifica se o usuário já existe
        if (this.userRepository.findByUsername(data.username()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        if(this.userRepository.findByEmail(data.email()).isPresent()){
            return ResponseEntity.badRequest().body("Email alredy registered by a user");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername(data.username());
        newUserEntity.setPassword(encryptedPassword);

        if (data.email() != null && !data.email().isBlank()) {
            newUserEntity.setEmail(data.email());
        }

        this.userRepository.save(newUserEntity);

        return ResponseEntity.ok("User registered successfully");
    }

}
