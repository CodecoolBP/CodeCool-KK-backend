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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/hardware")
public class HardwareController {

    private static final Logger logger = LoggerFactory.getLogger(HardwareController.class);

    private UserRepository userRepository;
    private CardRepository cardRepository;
    private TripRepository tripRepository;
    private StationRepository stationRepository;
    private UserMoneyCalculator userMoneyCalculator;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Autowired
    public void setTripRepository(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Autowired
    public void setStationRepository(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Autowired
    public void setUserMoneyCalculator(UserMoneyCalculator userMoneyCalculator) {
        this.userMoneyCalculator = userMoneyCalculator;
    }

    @PostMapping("/try-trip")
    public ResponseEntity<?> responseToOnSiteScanner(@RequestBody HardwareData hwData) {
        List<Station> stations = stationRepository.findAll();

        CckkUser userWantsToTravel = userRepository.findUserByCardNumber(hwData.getCardNumber());
        boolean isAuthorizedToTravel = userMoneyCalculator.checkIfUserCanTravel(userWantsToTravel, hwData.getCardNumber());
        int ticketPrice;

        for (Station station : stations) {
            if (hwData.getStationId().equals(station.getId())) {
                if (isAuthorizedToTravel){
                    ticketPrice = 350; //TODO: it is a magic number, change it with the business logic!
                } else {
                    ticketPrice = 0;
                }
                Trip newTrip = userMoneyCalculator.buildTrip(station, userWantsToTravel, isAuthorizedToTravel, ticketPrice);
                tripRepository.save(newTrip);
                logger.info(isAuthorizedToTravel + " " + userWantsToTravel.getEmail() +
                        " is " + isAuthorizedToTravel + " to travel!");
                return new ResponseEntity<>(isAuthorizedToTravel + " " + userWantsToTravel.getEmail() +
                        " is " + isAuthorizedToTravel + " to travel!", HttpStatus.CREATED);
            }
        }
        logger.warn("Something went wrong!");
        return new ResponseEntity<>("Something went wrong!", HttpStatus.CONFLICT);

        //TODO: check if user is authorized for travel: service.UserMoneyCalculation.checkIfUserCanTravel()
        //TODO: call trip builder and save to db: service.TripBuilder
        //      .saveTripToDb(userCanTravel:boolean,selectedUser:CckkUser,stationId:Long
    }
}
