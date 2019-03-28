package com.codecool.cckk.model.trips;

import com.codecool.cckk.model.CckkUser;

import com.codecool.cckk.model.station.Station;
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
//
    @Id
    @GeneratedValue
    private Long id;

    private int price = 350;
    private LocalDateTime journeyStart;

    @ManyToOne
    private CckkUser user;

    @OneToOne(cascade = CascadeType.ALL)
    private Station fromStation;
}
