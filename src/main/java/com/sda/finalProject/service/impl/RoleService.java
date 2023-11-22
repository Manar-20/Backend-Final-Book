package com.sda.finalProject.service.impl;



import com.sda.finalProject.entity.Role;
import com.sda.finalProject.entity.User;
import com.sda.finalProject.repository.RoleRepository;
import com.sda.finalProject.repository.UserRepository;
import com.sda.finalProject.service.interfaces.RoleServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleService implements RoleServiceInterface {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    public Role saveRole(Role role) {
        log.info("Saving a new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    public void addRoleToUser(String email, String roleName) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.setRole(role);
        userRepository.save(user);
    }
    @Override
    public Role findByName(String role) {
        return roleRepository.findByName(role);
    }
}