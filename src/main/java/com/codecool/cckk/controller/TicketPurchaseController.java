package com.codecool.cckk.controller;


import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.model.trips.Trip;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketPurchaseController {


    @GetMapping("/station")
    public ReturnMessage purchaseTicket(@RequestBody Trip trip){
        Boolean isTripVAlid = true;


      return new ReturnMessage(isTripVAlid, trip.toString());
    }
}
