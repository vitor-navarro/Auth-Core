package com.login.system.auth.core.pages.controllers;

import com.login.system.auth.core.entity.UserEntity;
import com.login.system.auth.core.enums.UserRole;
import com.login.system.auth.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @PostMapping("/adminlogin")
    public ModelAndView login(String username, String password) {
        ModelAndView mv = new ModelAndView("login");

        UserDetails userDetails = userRepository.findByUsername(username);

        if (userDetails instanceof UserEntity foundUser) {

            if (Objects.equals(foundUser.getPassword(), password) && foundUser.getRole() == UserRole.ADMIN) {
                return new ModelAndView("redirect:/admin/dashboard");
            } else if (foundUser.getRole() != UserRole.ADMIN) {
                mv.addObject("error", "O usuário inserido não é um administrador.");
            } else {
                mv.addObject("error", "Usuário ou senha inválidos");
            }
        } else {
            mv.addObject("error", "Usuário não encontrado.");
        }

        return mv;
    }



}
