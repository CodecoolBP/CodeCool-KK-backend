package com.codecool.cckk.controller;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.service.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserStorage userStorage;

    @GetMapping("/list")
    public List<CckkUser> getUsers() {
        return userStorage.getUsers();
    }

    @PostMapping("/add")
    public ReturnMessage addUser(@RequestBody @Valid CckkUser cckkUser) {
        return this.userStorage.addUser(cckkUser);

    }


    //TODO: put mapping
    //TODO: rest routing


}
