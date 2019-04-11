package com.codecool.cckk.controller;

import com.codecool.cckk.model.trips.Trip;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketPurchaseController {

    private static final Logger logger = LoggerFactory.getLogger(TicketPurchaseController.class);

    @GetMapping("/station")
    public ResponseEntity<?> purchaseTicket(@RequestBody Trip trip) {
        Boolean isTripVAlid = true;
        logger.info("Purchase ticket!");
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }
}
