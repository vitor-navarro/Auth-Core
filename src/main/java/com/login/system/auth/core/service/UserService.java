package com.login.system.auth.core.service;

import com.login.system.auth.core.dto.UserDTO;
import com.login.system.auth.core.entity.UserEntity;
import com.login.system.auth.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//TODO criação de um serviço de recuperação de senha
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO getUser(Integer id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        return userEntity.map(UserDTO::new).orElseGet(UserDTO::new);
    }

    public UserDTO getUserByUsername(String username) {
        UserDetails userDetails = userRepository.findByUsername(username);

        if (userDetails instanceof UserEntity) {
            return new UserDTO((UserEntity) userDetails);
        }

        return new UserDTO(); // Retorna um UserDTO vazio caso o usuário não seja encontrado ou não seja um UserEntity
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

    public boolean validatePassword(String username, String password) {
        UserDetails userDetails = userRepository.findByUsername(username);

        if (userDetails instanceof UserEntity userEntity) {
            return userEntity.getPassword().equals(password);
        }

        return false; // Retorna false se o usuário não for encontrado ou não for uma instância de UserEntity
    }


}