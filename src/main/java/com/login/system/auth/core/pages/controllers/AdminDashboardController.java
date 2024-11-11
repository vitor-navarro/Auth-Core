package com.login.system.auth.core.pages.controllers;

import com.login.system.auth.core.dto.UserDTO;
import com.login.system.auth.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminDashboardController {

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public ModelAndView adminDashboard(){
        ModelAndView mv = new ModelAndView("adminDashboard");

        List<UserDTO> users = userService.getAllUsers();
        mv.addObject("users", users);

        return mv;
    }

}
