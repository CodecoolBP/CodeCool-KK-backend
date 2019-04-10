package com.codecool.cckk.controller;

import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.model.station.Station;
import com.codecool.cckk.model.trips.Trip;
import com.codecool.cckk.repository.StationRepository;
import com.codecool.cckk.repository.TripRepository;
import com.codecool.cckk.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return new ResponseEntity<>("Unable to create. A station is already exists!",
                    HttpStatus.CONFLICT);
        }
        stationRepository.save(incomingStation);
        return new ResponseEntity<>(incomingStation, HttpStatus.CREATED);
    }

    @PostMapping("/adds")
    public ResponseEntity<?> addStations(@RequestBody @Valid List<Station> incomingStations) {
        List<Station> addedStation = stationService.fillStation(incomingStations);
        if (addedStation.isEmpty()) {
            return new ResponseEntity<>("Unable to create. All station is already exists!",
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(addedStation, HttpStatus.CREATED);
    }

    @PutMapping("/update/{stationID}")
    public ResponseEntity<?> updateStation(@PathVariable("stationID") Long stationID, @RequestBody Station station) {
        Station updatedStation = stationService.updateStation(stationID, station);
        if (updatedStation == null) {
            return new ResponseEntity<>("Unable to update. A station is not exists!",
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedStation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{stationID}")
    public ResponseEntity<?> deleteStation(@PathVariable("stationID") Long stationID) {
        Station deletedStation = stationService.deleteStation(stationID);
        if (deletedStation == null) {
            return new ResponseEntity<>("Unable to delete. A station is not exists!",
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedStation, HttpStatus.OK);
    }
}
