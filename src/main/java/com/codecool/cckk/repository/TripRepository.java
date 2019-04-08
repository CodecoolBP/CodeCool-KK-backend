package com.codecool.cckk.repository;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.trips.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("SELECT t FROM Trip t WHERE t.user = :user")
    ArrayList<Trip> findAllByUserId(@Param("user") CckkUser user);

}
