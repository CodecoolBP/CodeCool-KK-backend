package com.codecool.cckk.model.station;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Station {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;
    @NotNull
    private VehicleType vehicleType;
    @NotNull
    private int vehicleNumber;
    @NotEmpty
    private String address;


}
