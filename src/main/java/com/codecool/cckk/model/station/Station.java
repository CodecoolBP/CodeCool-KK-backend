package com.codecool.cckk.model.station;

import com.codecool.cckk.model.trips.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private TransportVehicle transportVehicle;
    private String address;


}
