package com.codecool.cckk.service;

import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.model.station.Station;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StationStorage {

    @Singular
    private List<Station> stations = new ArrayList<>();

    public ReturnMessage addStation(Station station) {
        //TODO add new station
        stations.add(station);
        //TODO check station is exists
        return new ReturnMessage(true, "Add new station success!");
    }

}
