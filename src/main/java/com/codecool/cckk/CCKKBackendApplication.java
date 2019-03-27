package com.codecool.cckk;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.Discount;
import com.codecool.cckk.model.Station;
import com.codecool.cckk.model.VehicleType;
import com.codecool.cckk.model.trips.Trip;
import com.codecool.cckk.repository.UserRepository;
import com.codecool.cckk.service.UserStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

@SpringBootApplication
public class CCKKBackendApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CCKKBackendApplication.class);

    @Autowired
    private UserStorage userStorage;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(CCKKBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return (String... args) -> {
            userStorage.addPremadeUsers();
            LOGGER.info(userStorage.toString());

            CckkUser zsoltika = CckkUser.builder()
                    .firstName("Zsoltika")
                    .lastName("Kovacs")
                    .hashedPassword("alma")
                    .email("zsoltika.k@ema.il")
                    .discount(Discount.STUDENT)
                    .build();

            Trip zsoltikaFirstTrip = Trip.builder()
                    .journeyStart(LocalDateTime
                            .of(2019,5,17,13,32,15))
                    .user(zsoltika)
                    .vehicleNumber(7)
                    .vehicleType(VehicleType.BUS)
                    .build();

            zsoltika.setTrips(Collections.singleton(zsoltikaFirstTrip));

            Station arany_janos_utca = Station.builder()
                    .name("Arany János utca")
                    .address("Budapest, Bajcsy-Zsilinszky út 25, 1065")
                    .build();
            zsoltikaFirstTrip.setFromStation(arany_janos_utca);

            userRepository.save(zsoltika);
        };
    }

}
