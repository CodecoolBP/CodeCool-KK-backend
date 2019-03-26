package com.codecool.cckk.controller;

import com.codecool.cckk.service.StationStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/station")
//@CrossOrigin(origins = "http://localhost:4200")
public class StationController {

    @Autowired
    private StationStorage stationStorage;



}
