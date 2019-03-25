package com.codecool.cckk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
//
    @Id
    @GeneratedValue
    private Long id;

    private int price = 350;
    private LocalDate journeyStart;

    @ManyToOne
    private Station fromStation;


    private VehicleType vehicleType;
    private int vehicleNumber;

//
    @ManyToOne
    private CckkUser user;
}
