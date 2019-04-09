package com.codecool.cckk.service;


import com.codecool.cckk.model.station.Station;
import com.codecool.cckk.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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

    public List<Station> fillStation(List<Station> stations) {
        List<Station> addedStation = new LinkedList<>();
        for (Station incomingStation : stations) {
            if (!stationIsExists(incomingStation)) {
                addedStation.add(incomingStation);
                stationRepository.save(incomingStation);
            }
        }
        return addedStation;
    }

    public Station updateStation(Long stationID, Station station) {
        List<Station> storedStations = stationRepository.findAll();
        for (Station storedStation : storedStations) {
            if (storedStation.getId().equals(stationID)) {
                storedStation.setName(station.getName());
                storedStation.setVehicleType(station.getVehicleType());
                storedStation.setVehicleNumber(station.getVehicleNumber());
                storedStation.setAddress(station.getAddress());
                stationRepository.save(storedStation);
                return storedStation;
            }
        }
        return null;
    }
}
