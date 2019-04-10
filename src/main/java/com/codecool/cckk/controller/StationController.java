package com.codecool.cckk.controller;

import com.codecool.cckk.model.station.Station;
import com.codecool.cckk.repository.StationRepository;
import com.codecool.cckk.repository.TripRepository;
import com.codecool.cckk.service.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger logger = LoggerFactory.getLogger(StationController.class);

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
        logger.info("Fetching all stations...");
        List<Station> stations = stationRepository.findAll();
        if (stations.isEmpty()) {
            logger.warn("Unable stations!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Fetch is SUCCESSFUL.");
        return new ResponseEntity<>(stations, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createStation(@RequestBody @Valid Station incomingStation) {
        logger.info("Creating a new Station.");
        if (stationService.stationIsExists(incomingStation)) {
            logger.warn("Unable to create. A station is already exists!");
            return new ResponseEntity<>("Unable to create. A station is already exists!",
                    HttpStatus.CONFLICT);
        }
        stationRepository.save(incomingStation);
        logger.info("Create is SUCCESSFUL.");
        return new ResponseEntity<>(incomingStation, HttpStatus.CREATED);
    }

    @PostMapping("/adds")
    public ResponseEntity<?> addStations(@RequestBody @Valid List<Station> incomingStations) {
        List<Station> addedStation = stationService.fillStation(incomingStations);
        logger.info("Creating a new Stations...");
        if (addedStation.isEmpty()) {
            logger.warn("Unable to create. All station is already exists!");
            return new ResponseEntity<>("Unable to create. All station is already exists!",
                    HttpStatus.CONFLICT);
        }
        logger.info("Create is SUCCESSFUL" +
                ". Incoming stations: " + incomingStations.size() +
                ". Created stations: " + addedStation.size());
        return new ResponseEntity<>(addedStation, HttpStatus.CREATED);
    }

    @PutMapping("/update/{stationID}")
    public ResponseEntity<?> updateStation(@PathVariable("stationID") Long stationID, @RequestBody Station station) {
        String updatedStationCondition = stationService.updateStation(stationID, station);
        logger.info("Updating station.");
        if (updatedStationCondition == null) {
            logger.warn("Unable to update. A station is not exists. Stations ID: " + stationID);
            return new ResponseEntity<>("Unable to update. A station is not exists!",
                    HttpStatus.NOT_FOUND);
        }
        if (updatedStationCondition.equals("Unable to update. The data is the same!")) {
            logger.warn("Unable to update. The data is the same!");
            return new ResponseEntity<>("Unable to update. The data is the same!",
                    HttpStatus.NOT_MODIFIED);
        }
        logger.info("Update is SUCCESSFUL.");
        return new ResponseEntity<>(stationService.findStation(stationID), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{stationID}")
    public ResponseEntity<?> deleteStation(@PathVariable("stationID") Long stationID) {
        Station deletedStation = stationService.deleteStation(stationID);
        logger.info("Deleting stations.");
        if (deletedStation == null) {
            logger.warn("Unable to delete. A station is not exists! Station ID: " + stationID);
            return new ResponseEntity<>("Unable to delete. A station is not exists!",
                    HttpStatus.NOT_FOUND);
        }
        logger.info("Delete is SUCCESSFUL.");
        return new ResponseEntity<>(deletedStation, HttpStatus.OK);
    }
}
