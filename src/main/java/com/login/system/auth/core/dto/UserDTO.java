package com.login.system.auth.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.login.system.auth.core.entity.UserEntity;
import com.login.system.auth.core.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("username")
    @NotNull
    @NotBlank
    public String username;

    @JsonProperty("email")
    @Email(message = "Email should be valid")
    public String email;

    @JsonProperty("role")
    @NotNull
    public UserRole role;

    public UserDTO(){}

    public UserDTO(UserEntity user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

}