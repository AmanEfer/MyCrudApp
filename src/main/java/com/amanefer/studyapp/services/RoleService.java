package com.amanefer.studyapp.services;

import com.amanefer.studyapp.models.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByName(String name);

    List<Role> getAllRoles();
}
