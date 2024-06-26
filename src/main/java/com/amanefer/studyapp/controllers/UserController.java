package com.amanefer.studyapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amanefer.studyapp.services.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userPage(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("user", userService.getUserByUsername(username));

        return "user/home";
    }
}
