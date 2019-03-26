package com.codecool.cckk.service;

import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.model.station.Station;
import com.codecool.cckk.repository.StationRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StationStorage {

    @Autowired
    private StationRepository stationRepository;

    @Singular
    private List<Station> stations = new ArrayList<>();

    public ReturnMessage addStation(Station station) {
        //TODO check station is exists
        stations.add(station);
        stationRepository.save(station);
        return new ReturnMessage(true, "Add new station success!");
    }

}
