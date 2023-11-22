package com.sda.finalProject.controller;


import com.sda.finalProject.entity.User;
import com.sda.finalProject.entity.dto.UserVerifyDTO;
import com.sda.finalProject.repository.RoleRepository;
import com.sda.finalProject.repository.UserRepository;
import com.sda.finalProject.service.impl.RoleService;
import com.sda.finalProject.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Valid User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    public UserVerifyDTO verifyToken(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User userFromDb = userRepository.findByEmail(email);
        UserVerifyDTO userVerifyDTO = new UserVerifyDTO(userFromDb.getName());
//        Gson gson = new Gson();
//        String userDetails = gson.toJson(userVerifyDTO);
        return userVerifyDTO;
    }
}