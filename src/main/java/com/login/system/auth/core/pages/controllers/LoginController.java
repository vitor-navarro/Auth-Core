package com.login.system.auth.core.pages.controllers;

import com.login.system.auth.core.entity.UserEntity;
import com.login.system.auth.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login(String username, String password){
        ModelAndView mv = new ModelAndView(("login"));

        Optional<UserEntity> user = userRepository.findByUsername(username);

        if(user.isPresent()){

            System.out.println("entrei no is present");
            UserEntity foundUser = user.get();

            if(Objects.equals(foundUser.getPassword(), password) && foundUser.isAdmin()){
                return new ModelAndView("adminDashboard");
            } else if (!foundUser.isAdmin()){
                mv.addObject("error", "O usuário inserido não é um administrador.");
            } else{
                mv.addObject("error", "Usuário ou senha inválidos");
            }
        } else{
            mv.addObject("error", "Usuário não encontrado.");
        }

        return mv;

    }

}
