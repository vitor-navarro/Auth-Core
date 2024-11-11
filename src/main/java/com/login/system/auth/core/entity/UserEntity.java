package com.login.system.auth.core.entity;

import com.login.system.auth.core.dto.UserCreateDTO;
import com.login.system.auth.core.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Entity
@Table(name = "Users")
@Getter @Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username; //Pode ser usado no login

    @Column(unique = true)
    private String email; //Pode ser usado no login

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "USER";

    public UserEntity() {
    }

    public UserEntity(UserDTO user){
        BeanUtils.copyProperties(user, this);
    }

    public UserEntity(UserCreateDTO user){
        BeanUtils.copyProperties(user, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}