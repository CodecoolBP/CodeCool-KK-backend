package com.codecool.cckk.service;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.repository.TripRepository;
import com.codecool.cckk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class UserMoneyCalculation {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TripRepository tripRepository;

    public ReturnMessage checkIfUserCanTravel(@RequestBody CckkUser user) {
        //TODO: user has purchased pass
        //TODO: user has traveled enough to acquire pass
        //TODO: user has money


        return new ReturnMessage(true, "Nothing happened yet");
    }

}
