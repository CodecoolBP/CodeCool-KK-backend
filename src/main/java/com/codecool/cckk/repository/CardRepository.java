package com.codecool.cckk.repository;

import com.codecool.cckk.model.cards.PrePaidCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<PrePaidCard, Long> {

    @Modifying
    @Query("update PrePaidCard C SET C.balance = :newBalance where C.cardNumber = :cardNumber")
    double setNewBalance(@Param("newBalance") Double newBalance, @Param("cardNumber") Long cardNumber);
}
