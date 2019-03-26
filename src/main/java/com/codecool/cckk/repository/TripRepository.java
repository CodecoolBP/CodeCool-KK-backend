package com.codecool.cckk.repository;

import com.codecool.cckk.model.trips.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
