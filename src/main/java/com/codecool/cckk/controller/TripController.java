package com.codecool.cckk.controller;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.trips.Trip;
import com.codecool.cckk.repository.TripRepository;
import com.codecool.cckk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TripController {

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
    public List<Trip> getTrips(@PathVariable("email") String email){
        System.out.println(email);
        CckkUser user = userRepository.findUserByEmail(email);
        System.out.println(user.getFirstName() + " " + user.getLastName());
        System.out.println(user.getEmail());

        System.out.println(user.getId());


        ArrayList<Trip> trips = tripRepository.findAllByUserId(user);

        for (Trip trip : trips) {
            System.out.println(trip.getFromStation());
        }

        if (trips.isEmpty()) {
            return null;
        }
        return trips;
    }
}
