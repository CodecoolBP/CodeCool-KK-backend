package com.codecool.cckk.controller;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.trips.Trip;
import com.codecool.cckk.repository.TripRepository;
import com.codecool.cckk.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TripController {

    private static final Logger logger = LoggerFactory.getLogger(StationController.class);

    private UserRepository userRepository;
    private TripRepository tripRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setTripRepository(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @GetMapping("/{email}/history")
    public ResponseEntity<?> getTrips(@PathVariable("email") String email){
        logger.info("Creating history for " + email);
        CckkUser user = userRepository.findUserByEmail(email);
        if (user == null) {
            logger.warn("There is no user whose email address is" + email + ".");
            return new ResponseEntity<>("There is no user whose email address is" + email + ".", HttpStatus.NOT_FOUND);
        }
        ArrayList<Trip> trips = tripRepository.findAllByUserId(user);
        if (trips.isEmpty()) {
            logger.warn("No trips to the user. UserID: " + user.getId());
            return new ResponseEntity<>("No trips to the user.", HttpStatus.NOT_FOUND);
        }
        logger.info("UserID: " + user.getId()
                + ", trips: " + trips.size() + "pcs");
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }
}
