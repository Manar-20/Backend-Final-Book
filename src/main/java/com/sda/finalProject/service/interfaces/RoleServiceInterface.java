package com.sda.finalProject.service.interfaces;


import com.sda.finalProject.entity.Role;

public interface RoleServiceInterface {
    Role saveRole(Role role);

    void addRoleToUser(String email, String roleName);

    Role findByName(String role);
}