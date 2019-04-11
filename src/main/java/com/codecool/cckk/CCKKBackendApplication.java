package com.codecool.cckk;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.Discount;
import com.codecool.cckk.model.station.Station;
import com.codecool.cckk.model.station.VehicleType;
import com.codecool.cckk.model.trips.Trip;
import com.codecool.cckk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CCKKBackendApplication {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(CCKKBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            CckkUser testUser = CckkUser.builder()
                    .email("test@gmail.com")
                    .password("test")
                    .firstName("test")
                    .lastName("test")
                    .discount(Discount.FULL_PRICE)
                    .build();

            Station testStation = Station.builder()
                    .name("Vörösmarty tér")
                    .vehicleType(VehicleType.METRO)
                    .vehicleNumber(1)
                    .address("V. Vörösmarty tér")
                    .build();

            Trip testTrip = Trip.builder()
                    .price(10)
                    .success(true)
                    .journeyStart(LocalDateTime.now())
                    .user(testUser)
                    .fromStation(testStation)
                    .vehicleNumber(testStation.getVehicleNumber())
                    .vehicleType(testStation.getVehicleType())
                    .build();

            Set<Trip> trips = new HashSet<>();
            trips.add(testTrip);

            testUser.setTrips(trips);

            userRepository.save(testUser);
        };
    }
}
