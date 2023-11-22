package com.sda.finalProject.service.interfaces;



import com.sda.finalProject.entity.User;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(User userSignupDTO);

    List<User> getUsers();

    User getUserByName(String name);
}