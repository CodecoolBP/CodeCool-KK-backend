package com.codecool.cckk.service;

import com.codecool.cckk.model.station.Station;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationStorage {

    @Singular
    private static List<Station> stations = new ArrayList<>();

}
