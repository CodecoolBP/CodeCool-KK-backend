package com.codecool.cckk.repository;

import com.codecool.cckk.model.cards.PrePaidCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<PrePaidCard, Long> {
}
