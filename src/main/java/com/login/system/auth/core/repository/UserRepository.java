package com.login.system.auth.core.repository;

import com.login.system.auth.core.entity.UserEntity;
import com.login.system.auth.core.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserDetails findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByRole(UserRole role);
}