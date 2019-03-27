package com.codecool.cckk.model.trips;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.Station;
import com.codecool.cckk.model.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trip {

    @Id
    @GeneratedValue
    private Long id;

    private int price = 350;
    private LocalDateTime journeyStart;

    private VehicleType vehicleType;
    private int vehicleNumber;


    @ManyToOne
    private CckkUser user;

    @OneToOne(cascade = CascadeType.ALL)
    private Station fromStation;
}
