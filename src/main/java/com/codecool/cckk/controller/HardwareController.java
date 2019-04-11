package com.codecool.cckk.controller;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.HardwareData;
import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.model.station.Station;
import com.codecool.cckk.model.trips.Trip;
import com.codecool.cckk.repository.CardRepository;
import com.codecool.cckk.repository.StationRepository;
import com.codecool.cckk.repository.TripRepository;
import com.codecool.cckk.repository.UserRepository;
import com.codecool.cckk.service.UserMoneyCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/hardware")
public class HardwareController {

    @Autowired  
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    StationRepository stationRepository;

    @Autowired
    UserMoneyCalculator userMoneyCalculator;

    @PostMapping("/try-trip")
    public ReturnMessage responseToOnSiteScanner(@RequestBody HardwareData hwData) {
        List<Station> stations = stationRepository.findAll();

        CckkUser userWantsToTravel = userRepository.findUserByCardNumber(hwData.getCardNumber());
        boolean isAuthorizedToTravel = userMoneyCalculator.checkIfUserCanTravel(userWantsToTravel, hwData.getCardNumber());
        int ticketPrice;

        for (Station station : stations) {
            if (hwData.getStationId().equals(station.getId())) {
                if  (isAuthorizedToTravel){
                    ticketPrice = 350; //TODO: it is a magic number, change it with the business logic!
                }else{
                    ticketPrice = 0;
                }
                Trip newTrip = buildTrip(station, userWantsToTravel, isAuthorizedToTravel, ticketPrice);
                tripRepository.save(newTrip);
                return new ReturnMessage(isAuthorizedToTravel, userWantsToTravel.getEmail() +
                        " is " + isAuthorizedToTravel + " to travel!");
                //TODO: should be http response!!!
            }

        }
        return new ReturnMessage(false, "Something went wrong!");

        //TODO: check if user is authorized for travel: service.UserMoneyCalculation.checkIfUserCanTravel()
        //TODO: call trip builder and save to db: service.TripBuilder
        //      .saveTripToDb(userCanTravel:boolean,selectedUser:CckkUser,stationId:Long

    }


    private Trip buildTrip(Station station, CckkUser user, boolean isValid, int ticketprice) {
        Trip newTrip = Trip.builder()
                .fromStation(station)
                .journeyStart(LocalDateTime.now())
                .vehicleType(station.getVehicleType())
                .vehicleNumber(station.getVehicleNumber())
                .price(ticketprice)
                .user(user)
                .success(isValid)
                .build();
        return newTrip;
    }

}
