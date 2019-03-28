package com.codecool.cckk.controller;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.HardwareData;
import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.model.cards.PrePaidCard;
import com.codecool.cckk.model.station.Station;
import com.codecool.cckk.model.trips.Trip;
import com.codecool.cckk.repository.CardRepository;
import com.codecool.cckk.repository.StationRepository;
import com.codecool.cckk.repository.TripRepository;
import com.codecool.cckk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
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


    @PostMapping("/try-trip")
    public ReturnMessage responseToOnSiteScanner(@RequestBody HardwareData hwData) {
        List<Station> stations = stationRepository.findAll();
        List<CckkUser> users = userRepository.findAll();
        double userBalance;


        for (Station station : stations) {
            if (hwData.getStationId().equals(station.getId())) {
                for (CckkUser user : users) {
                    System.out.println(user.toString());
                    for (PrePaidCard card : user.getCards()) {
                        System.out.println(card.toString());
                        if (card.getCardNumber().equals(hwData.getCardNumber())) {
                            userBalance = card.getBalance();
                            boolean isValid = true;
                            if(userBalance<350){
                                isValid = false;
                            }
                            Trip newTrip = buildTrip(station, user, isValid, 350);
                            if(isValid){
                                cardRepository.setNewBalance(userBalance-350.00, card.getCardNumber());
                            }

                            return new ReturnMessage(isValid, newTrip.toString());
                        }
                    }
                }
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
        tripRepository.save(newTrip);
        return newTrip;
    }

}
