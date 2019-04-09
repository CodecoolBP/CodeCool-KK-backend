package com.codecool.cckk.controller;

import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.model.station.Station;
import com.codecool.cckk.model.trips.Trip;
import com.codecool.cckk.repository.StationRepository;
import com.codecool.cckk.repository.TripRepository;
import com.codecool.cckk.service.StationService;
import com.codecool.cckk.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/station")
//@CrossOrigin(origins = "http://localhost:4200")
public class StationController {

    private StationRepository stationRepository;
    private TripRepository tripRepository;
    private StationService stationService;

    @Autowired
    public void setStationRepository(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Autowired
    public void setTripRepository(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Station>> getStations() {
        List<Station> stations = stationRepository.findAll();
        if (stations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(stations, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStation(@RequestBody @Valid Station incomingStation) {
        if (stationService.stationIsExists(incomingStation)) {
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to create. A station is already exists!"),
                    HttpStatus.CONFLICT);
        }
        stationRepository.save(incomingStation);
        return new ResponseEntity<>(incomingStation, HttpStatus.CREATED);
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
                List<Trip> trips = tripRepository.findAll();
                for (Trip trip : trips) {
                    if (trip.getFromStation().equals(storedStation)) {
                        tripRepository.delete(trip);
                    }
                }
                stationRepository.delete(storedStation);
                return new ReturnMessage(true, storedStation.toString() + "This station deleted!");
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
