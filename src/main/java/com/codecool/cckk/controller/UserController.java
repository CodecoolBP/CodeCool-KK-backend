package com.codecool.cckk.controller;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.repository.UserRepository;
import com.codecool.cckk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CckkUser>> getUsers() {
        logger.info("Fetching all users...");
        List<CckkUser> users = userRepository.findAll();
        if (users.isEmpty()) {
            logger.warn("Unable users");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Fetch is SUCCESSFUL.");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid CckkUser incomingUser) {
        logger.info("Creating a new User...");
        if (userService.emailIsExists(incomingUser)) {
            logger.warn("Unable to create. A user already exists!");
            return new ResponseEntity<>("Unable to create. A user already exists!", HttpStatus.CONFLICT);
        }
        userRepository.save(incomingUser);
        logger.info("Create is SUCCESSFUL. New User e-mail: " + incomingUser.getEmail());
        return new ResponseEntity<>(incomingUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody CckkUser loginUser){
        logger.info("Logging " + loginUser.getEmail() + " ...");
        String errorBody = "Incorrect e-mail address or password.";
        if (!userService.emailIsExists(loginUser)) {
            logger.warn("Unable to login. This email address is not in the database!");
            return new ResponseEntity<>(errorBody, HttpStatus.FORBIDDEN);
        }
        if (!userService.passwordIsMatches(loginUser)) {
            logger.warn("Unable to login. This password is incorrect!");
            return new ResponseEntity<>(errorBody, HttpStatus.FORBIDDEN);
        }
        logger.info("Login is SUCCESSFUL. E-mail: " + loginUser.getEmail());
        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }

    //TODO: put mapping
    //TODO: rest routing


}
