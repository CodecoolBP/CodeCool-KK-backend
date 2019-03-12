package com.codecool.cckk.controller;

import com.codecool.cckk.model.User;
import com.codecool.cckk.service.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserStorage userStorage;

    @GetMapping
    public List<User> getUsers(){
        return userStorage.getUsers();
    }
}
