package com.codecool.cckk.repository;

import com.codecool.cckk.model.cards.PrePaidCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface CardRepository extends JpaRepository<PrePaidCard, Long> {

    @Modifying
    @Query("UPDATE PrePaidCard C SET C.balance = ?1 WHERE C.cardNumber = ?2")
    @Transactional
    void setNewBalance(double newBalance, Long cardNumber);

}
