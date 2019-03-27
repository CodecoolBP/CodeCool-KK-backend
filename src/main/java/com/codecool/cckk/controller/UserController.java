package com.codecool.cckk.controller;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public List<CckkUser> getUsers() {
        this.userRepository.findAll();
        return this.userRepository.findAll();
    }

    @PostMapping("/add")
    public ReturnMessage addUser(@RequestBody @Valid CckkUser cckkUser) {
        List<CckkUser> users = this.userRepository.findAll();
        for (CckkUser user : users) {
            if (user.getEmail().equals(cckkUser.getEmail())) {
                return new ReturnMessage(false, "Email address is already taken!");
            }
        }
        this.userRepository.save(cckkUser);
        return new ReturnMessage(true, "Registration is success!");

    }


}
