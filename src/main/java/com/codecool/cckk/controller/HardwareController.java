package com.codecool.cckk.controller;

import com.codecool.cckk.model.HardwareData;
import com.codecool.cckk.model.ReturnMessage;
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

    @PostMapping("/query")
    public ReturnMessage responseToOnSiteScanner(@RequestBody HardwareData hwData) {
        //TODO: query user and station from db
        //TODO: check if user is authorized for travel: service.UserMoneyCalculation.checkIfUserCanTravel()
        //TODO: call trip builder and save to db: service.TripBuilder
        //      .saveTripToDb(userCanTravel:boolean,selectedUser:CckkUser,stationId:Long

        return new ReturnMessage(true, "Nothing happened yet");
    }

}
