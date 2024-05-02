package com.amanefer.studyapp.controllers;

import com.amanefer.studyapp.models.Role;
import com.amanefer.studyapp.models.User;
import com.amanefer.studyapp.services.RegistrationService;
import com.amanefer.studyapp.services.UserService;
import com.amanefer.studyapp.util.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final RegistrationService registrationService;


    @Autowired
    public AdminController(UserService userService, UserValidator userValidator,
                           RegistrationService registrationService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.registrationService = registrationService;
    }

    @GetMapping
    public String showAllPeople(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", userService.getUserByUsername(username));
        model.addAttribute("newUser", new User());
        return "admin/admin";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("newUser") @Valid User user,
                             @RequestParam("selectedRole") String selectedRole,
                             BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/admin/new";
        }

        registrationService.register(user, selectedRole);

        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id,
                             @RequestParam("selectedRole") String selectedRole) {
        user.getRole().add(new Role(selectedRole));

        userService.updateUser(id, user);

        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);

        return "redirect:/admin";
    }
}
