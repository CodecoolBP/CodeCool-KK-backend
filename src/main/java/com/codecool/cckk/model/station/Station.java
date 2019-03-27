package com.codecool.cckk.model.station;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Station {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private TransportVehicle transportVehicle;
    private String address;


}
