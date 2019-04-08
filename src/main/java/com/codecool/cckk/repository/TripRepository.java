package com.codecool.cckk.repository;

import com.codecool.cckk.model.trips.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("SELECT u FROM Trip u WHERE u.user = :userId")
    List<Trip> findAllByUserId(@Param("userId") Long userId);

}
