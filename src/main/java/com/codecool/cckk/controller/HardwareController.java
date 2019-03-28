package com.codecool.cckk.controller;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.repository.TripRepository;
import com.codecool.cckk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hardware")
public class HardwareController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TripRepository tripRepository;

    @PostMapping("/query")
    public ReturnMessage checkIfUserCanTravel(@RequestBody CckkUser user) {
        //TODO: user has purchased pass
        //TODO: user has traveled enough to acquire pass
        //TODO: user has money


        return new ReturnMessage(true, "Nothing happened yet");
    }

}
