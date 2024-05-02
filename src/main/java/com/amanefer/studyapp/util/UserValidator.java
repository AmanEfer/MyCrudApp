package com.amanefer.studyapp.util;

import com.amanefer.studyapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.amanefer.studyapp.repositories.UserRepository;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User checkedPerson = (User) target;
        Optional<User> foundUser = userRepository.findUserByUsername(checkedPerson.getUsername());

        if (foundUser.isPresent()) {
            errors.rejectValue("username", "", "User already exists");
        }
    }
}
