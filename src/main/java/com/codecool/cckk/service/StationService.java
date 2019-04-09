package com.codecool.cckk.service;


import com.codecool.cckk.model.station.Station;
import com.codecool.cckk.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private StationRepository stationRepository;

    @Autowired
    public void setStationRepository(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public boolean stationIsExists(Station incomingStation) {
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
