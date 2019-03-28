package com.codecool.cckk;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.Discount;
import com.codecool.cckk.model.station.Station;
import com.codecool.cckk.model.station.VehicleType;
import com.codecool.cckk.model.trips.Trip;
import com.codecool.cckk.repository.StationRepository;
import com.codecool.cckk.repository.TripRepository;
import com.codecool.cckk.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AllRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private StationRepository stationRepository;

    @Test
    public void userIsSaved() {
        CckkUser zsoltika = createZsoltika();
        userRepository.save(zsoltika);
        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    public void tripIsSaved() {
        Trip zsoltikaFirstTrip = createFirstTrip();
        tripRepository.save(zsoltikaFirstTrip);
        assertEquals(1, tripRepository.findAll().size());
    }

    @Test
    public void stationIsSaved() {
        Station arany_janos_utca = createStationArany();
        stationRepository.save(arany_janos_utca);
        assertEquals(1, stationRepository.findAll().size());
    }

    @Test
    public void userSavesTripAndStation() {
        CckkUser zsoltika = createZsoltika();
        Trip zsoltikaFirstTrip = createFirstTrip();
        zsoltikaFirstTrip.setUser(zsoltika);
        zsoltika.setTrips(Collections.singleton(zsoltikaFirstTrip));
        Station arany_janos_utca = createStationArany();
        zsoltikaFirstTrip.setFromStation(arany_janos_utca);
        userRepository.save(zsoltika);
        assertEquals(1,tripRepository.findAll().size());
        assertEquals(1,stationRepository.findAll().size());
    }

    @Test
    public void selectSingleUserFromDbFromEmail() {
        CckkUser zsoltika = createZsoltika();
        userRepository.save(zsoltika);

        CckkUser userFromDb = userRepository.findUserByEmail(zsoltika.getEmail());
        assertEquals(userFromDb, zsoltika);
        assertEquals("Zsoltika",userFromDb.getFirstName());
        assertEquals("zsoltika.k@ema.il",userFromDb.getEmail());
        assertEquals(Discount.STUDENT,userFromDb.getDiscount());
    }


    @Test
    public void selectingSingleUserFromDbFromCardNumber() {
        CckkUser zsoltika = createZsoltika();
        userRepository.save(zsoltika);


    }


    private Station createStationArany() {
        return Station.builder()
                .name("Arany János utca")
                .address("Budapest, Bajcsy-Zsilinszky út 25, 1065")
                .vehicleNumber(3)
                .vehicleType(VehicleType.METRO)
                .build();

    }

    private Trip createFirstTrip() {
        return Trip.builder()
                .journeyStart(LocalDateTime
                        .of(2019,5,17,13,32,15))
                .build();
    }

    private CckkUser createZsoltika() {
        return CckkUser.builder()
                    .firstName("Zsoltika")
                    .lastName("Kovacs")
                    .password("alma")
                    .email("zsoltika.k@ema.il")
                    .discount(Discount.STUDENT)
                    .build();
    }
}
