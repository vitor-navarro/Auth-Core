package com.login.system.auth.core.service;

import com.login.system.auth.core.dto.UserCreateDTO;
import com.login.system.auth.core.dto.UserDTO;
import com.login.system.auth.core.entity.UserEntity;
import com.login.system.auth.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//TODO serviço de mudança e recuperação de senha
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(UserCreateDTO user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DataIntegrityViolationException("O nome de usuário já está em uso.");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("O e-mail já está em uso.");
        }

        UserEntity userEntity = new UserEntity(user);
        return userRepository.save(userEntity);
    }

    public UserDTO getUser(Integer id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        return userEntity.map(UserDTO::new).orElseGet(UserDTO::new);
    }

    public UserDTO getUserByUsername(String username){
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        return userEntity.map(UserDTO::new).orElseGet(UserDTO::new);
    }

    public List<UserDTO> getAllUsers(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public UserDTO updateUser(UserDTO user){
        UserEntity userEntity = userRepository.findById(user.getId()).get();

        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());

        return new UserDTO(userRepository.save(userEntity));
    }

    public void deleteUserById(Integer id){
        UserEntity userEntity = userRepository.findById(id).get();
        userRepository.delete(userEntity);
    }

    public boolean validatePassword(String username, String password){
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        return userEntity.get().getPassword().equals(password);
    }

}