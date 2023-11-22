package com.sda.finalProject.controller;


import com.sda.finalProject.entity.User;
import com.sda.finalProject.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/all-users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userService.getUsers();
    }
    @GetMapping("/name/{name}")
    public User findByName (@PathVariable String name) throws Exception {
        return userService.getUserByName(name);
    }
}
