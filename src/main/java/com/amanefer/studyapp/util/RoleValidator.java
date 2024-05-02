package com.amanefer.studyapp.util;

import com.amanefer.studyapp.models.Role;
import com.amanefer.studyapp.models.User;
import org.springframework.stereotype.Component;

@Component
public class RoleValidator {

    public void addRole(User user, String selectedRole) {
        if (!validate(user, selectedRole)) {
            user.getRole().add(new Role(selectedRole));
        }
    }

    private boolean validate(User user, String checkingRole) {
        return user.getRole().stream()
                .map(Role::getName)
                .anyMatch(roleName -> roleName.equalsIgnoreCase(checkingRole));
    }
}
