package com.codecool.cckk.controller;

import com.codecool.cckk.model.station.Station;
import com.codecool.cckk.service.StationStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
