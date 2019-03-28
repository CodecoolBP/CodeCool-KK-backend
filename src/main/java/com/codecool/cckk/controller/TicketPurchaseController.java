package com.codecool.cckk.controller;


import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.model.trips.Trip;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class TicketPurchaseController {


    @GetMapping("/station")
    public ReturnMessage purchaseTicket(@RequestBody Trip trip){
        Boolean isTripVAlid = true;


      return new ReturnMessage(isTripVAlid, trip.toString());
    }
}
