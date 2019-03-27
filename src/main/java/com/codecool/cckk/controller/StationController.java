package com.codecool.cckk.controller;

import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.model.station.Station;
import com.codecool.cckk.service.StationStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/station")
//@CrossOrigin(origins = "http://localhost:4200")
public class StationController {

    @Autowired
    private StationStorage stationStorage;

    @GetMapping("/list")
    public List<Station> getStations() {
        return stationStorage.getStations();
    }

    @PostMapping("/add")
    public ReturnMessage addStation(@RequestBody @Valid Station station) {
        return stationStorage.addStation(station);
    }

    @PostMapping("/adds")
    public ReturnMessage addStations(@RequestBody @Valid List<Station> stations) {
        return stationStorage.addStations(stations);
    }



}
