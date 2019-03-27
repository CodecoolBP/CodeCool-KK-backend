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
    public ReturnMessage addUser(@RequestBody @Valid CckkUser newUser) {
        List<CckkUser> users = getUsers();
        for (CckkUser user : users) {
            if (user.getEmail().equals(newUser.getEmail())) {
                return new ReturnMessage(false, "Email address is already taken!");
            }
        }
        this.userRepository.save(newUser);
        this.userRepository.save(newUser);
        loginUser(newUser);
        return new ReturnMessage(true, "Registration is success!");

    }

    @GetMapping("/login")
    public ReturnMessage loginUser(@RequestBody CckkUser loginUser){
        List<CckkUser> users = getUsers();
        for (CckkUser user : users){
            if (user.getEmail().equals(loginUser.getEmail())){
                if(user.getHashedPassword().equals(loginUser.getHashedPassword())){
                    return new ReturnMessage(true, "Login successful");
                }else{
                    return new ReturnMessage(false, "Password is incorrect");
                }
            }
        }
        return new ReturnMessage(false, "Email address is incorrect, no user found!");
    }
    @GetMapping("/login")
    public ReturnMessage loginUser(@RequestBody CckkUser loginUser){
        List<CckkUser> users = getUsers();
        for (CckkUser user : users){
            if (user.getEmail().equals(loginUser.getEmail())){
                if(user.getPassword().equals(loginUser.getPassword())){
                    return new ReturnMessage(true, "Login successful");
                }else{
                    return new ReturnMessage(false, "Password is incorrect");
                }
            }
        }
        return new ReturnMessage(false, "Email address is incorrect, no user found!");
    }

    //TODO: put mapping
    //TODO: rest routing


}
