package com.amanefer.studyapp.services;

import com.amanefer.studyapp.models.User;
import com.amanefer.studyapp.util.RoleValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleValidator roleValidator;

    @Autowired
    public RegistrationService(UserService userService, PasswordEncoder passwordEncoder, RoleValidator roleValidator) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleValidator = roleValidator;
    }

    @Transactional
    public void register(User user, String roleName) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        roleValidator.addRole(user, roleName);

        userService.saveUser(user);
    }
}
