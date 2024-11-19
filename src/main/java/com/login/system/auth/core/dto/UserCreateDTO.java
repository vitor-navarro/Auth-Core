package com.login.system.auth.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.login.system.auth.core.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

//TODO remover o userCreateDTO, após a implementação do Spring Security a responsabilidade de criação de usuário foi transferida do userController para o AuthenticationController.
@Getter
@Setter
public class UserCreateDTO {
    @JsonProperty("id")
    public Integer id;

    @JsonProperty("username")
    @NotNull
    @NotBlank
    public String username;

    @JsonProperty("password")
    @Size(message = "Password should be longer than 7 caracters", min = 7)
    @NotNull
    public String password;

    @JsonProperty("email")
    @Email(message = "Email should be valid")
    public String email;

    public UserCreateDTO() {
    }

    public UserCreateDTO(UserEntity user) {
        BeanUtils.copyProperties(user, this);
    }
}