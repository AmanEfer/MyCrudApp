package com.amanefer.studyapp.services;

import com.amanefer.studyapp.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(Long id);
    User getUserByUsername(String username);

    void saveUser(User person);

    void updateUser(Long id, User person);

    void deleteUser(Long id);
}
