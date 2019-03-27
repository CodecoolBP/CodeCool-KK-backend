package com.codecool.cckk.controller;

import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.model.station.Station;
import com.codecool.cckk.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/station")
//@CrossOrigin(origins = "http://localhost:4200")
public class StationController {

    @Autowired
    private StationRepository stationRepository;

    @GetMapping("/list")
    public List<Station> getStations() {
        return stationRepository.findAll();
    }

    @PostMapping("/add")
    public ReturnMessage addStation(@RequestBody @Valid Station incomingStation) {
        if (stationIsExists(incomingStation)) {
            stationRepository.save(incomingStation);
            return new ReturnMessage(true, "This station was saved!");
        } else if (!stationIsExists(incomingStation)) {
            return new ReturnMessage(false, "This station already exists!!");
        } else {
            return new ReturnMessage(false, "Something went wrong!");
        }
    }

    @PostMapping("/adds")
    public ReturnMessage addStations(@RequestBody @Valid List<Station> incomingStations) {
        int saved = 0;
        int exists = 0;

        for (Station incomingStation : incomingStations) {
            if (stationIsExists(incomingStation)) {
                saved++;
                stationRepository.save(incomingStation);
            } else if (!stationIsExists(incomingStation)) {
                exists++;
            } else {
                return new ReturnMessage(false, "Something went wrong!");
            }
        }
        if (saved == 0) {
            return new ReturnMessage(false, "Save: " + saved + ", exists: " + exists);
        } else {
            return new ReturnMessage(true, "Save: " + saved + ", exists: " + exists);
        }
    }

    private boolean stationIsExists(Station incomingStation) {
        List<Station> storedStations = stationRepository.findAll();
        for (Station storedStation : storedStations) {
            if (incomingStation.getName().equals(storedStation.getName()) &
                incomingStation.getAddress().equals(storedStation.getAddress()) &
                incomingStation.getTransportVehicle().equals(storedStation.getTransportVehicle())) {
                return false;
            }
        }
        return true;
    }


    public ReturnMessage updateStation() {


        return new ReturnMessage(false, "test");
    }
}
