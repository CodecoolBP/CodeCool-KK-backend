package com.codecool.cckk.model.trips;

import com.codecool.cckk.model.CckkUser;
//import com.codecool.cckk.model.station.Station;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    @Id
    @GeneratedValue
    private Long id;

    @EqualsAndHashCode.Exclude
    private int price = 350;
    private LocalDate journeyStart;

    private VehicleType vehicleType;
    private int vehicleNumber;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cckk_user_id")
    private CckkUser user;
}
