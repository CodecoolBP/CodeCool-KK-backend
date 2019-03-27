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
        if (!stationIsExists(incomingStation)) {
            stationRepository.save(incomingStation);
            return new ReturnMessage(true, "This station was saved!");
        } else if (stationIsExists(incomingStation)) {
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
            if (!stationIsExists(incomingStation)) {
                saved++;
                stationRepository.save(incomingStation);
            } else if (stationIsExists(incomingStation)) {
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

    @PutMapping("/update/{stationID}")
    public ReturnMessage updateStation(@PathVariable("stationID") Long stationID, @RequestBody Station station) {
        List<Station> storedStations = stationRepository.findAll();
        for (Station storedStation : storedStations) {
            if (storedStation.getId().equals(stationID)) {
                storedStation.setName(station.getName());
                storedStation.setVehicleType(station.getVehicleType());
                storedStation.setVehicleNumber(station.getVehicleNumber());
                storedStation.setAddress(station.getAddress());
                stationRepository.save(storedStation);
                return new ReturnMessage(true, "Update is successful!");
            }
        }
        return new ReturnMessage(false, "Update isn't successful, because not matches!");
    }

    @DeleteMapping("/delete/{stationID}")
    public ReturnMessage deleteStation(@PathVariable("stationID") Long stationID) {
        List<Station> storedStations = stationRepository.findAll();
        for (Station storedStation : storedStations) {
            if (storedStation.getId().equals(stationID)) {
                stationRepository.delete(storedStation);
                return new ReturnMessage(true, "This station deleted!");
            }
        }

        return new ReturnMessage(false, "This station doesn't exists!");
    }



    private boolean stationIsExists(Station incomingStation) {
        List<Station> storedStations = stationRepository.findAll();
        for (Station storedStation : storedStations) {
            if (incomingStation.getName().equals(storedStation.getName()) &
                incomingStation.getAddress().equals(storedStation.getAddress()) &
                incomingStation.getVehicleType().equals(storedStation.getVehicleType()) &
                incomingStation.getVehicleNumber() == storedStation.getVehicleNumber()) {
                return true;
            }
        }
        return false;
    }
}
