package com.login.system.auth.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @JsonProperty("username")
        @NotNull(message = "Username is required")
        @NotBlank(message = "Username cannot be blank")
        String username,

        @JsonProperty("password")
        @NotNull(message = "Password is required")
        @Size(min = 7, message = "Password should be at least 7 characters long")
        String password,

        @JsonProperty("email")
        @Email(message = "Email should be valid")
        String email // Opcional
) {}
