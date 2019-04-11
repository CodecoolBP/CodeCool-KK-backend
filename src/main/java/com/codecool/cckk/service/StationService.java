package com.codecool.cckk.service;


import com.codecool.cckk.model.station.Station;
import com.codecool.cckk.model.trips.Trip;
import com.codecool.cckk.repository.StationRepository;
import com.codecool.cckk.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StationService {

    private StationRepository stationRepository;
    private TripRepository tripRepository;

    @Autowired
    public void setStationRepository(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Autowired
    public void setTripRepository(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
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

    public String updateStation(Long stationID, Station station) {
        List<Station> storedStations = stationRepository.findAll();
        for (Station storedStation : storedStations) {
            if (storedStation.getId().equals(stationID)) {
                if (storedStation.getName().equals(station.getName()) &
                    storedStation.getName().equals(station.getName()) &
                    storedStation.getVehicleType().equals(station.getVehicleType()) &
                    storedStation.getVehicleNumber() == storedStation.getVehicleNumber()) {
                    return "Unable to update. The data is the same!";
                }
                storedStation.setName(station.getName());
                storedStation.setVehicleType(station.getVehicleType());
                storedStation.setVehicleNumber(station.getVehicleNumber());
                storedStation.setAddress(station.getAddress());
                stationRepository.save(storedStation);
                return "Updated!";
            }
        }
        return null;
    }

    public Station findStation (Long stationID) {
        List<Station> stations = stationRepository.findAll();
        for (Station station : stations) {
            if (station.getId().equals(stationID)) {
                return station;
            }
        }
        return null;
    }

    public Station deleteStation (Long stationID) {
        List<Station> storedStations = stationRepository.findAll();
        for (Station storedStation : storedStations) {
            if (storedStation.getId().equals(stationID)) {
                List<Trip> trips = tripRepository.findAll();
                for (Trip trip : trips) {
                    if (trip.getFromStation().equals(storedStation)) {
                        tripRepository.delete(trip);
                    }
                }
                stationRepository.delete(storedStation);
                return storedStation;
            }
        }
        return null;
    }
}
