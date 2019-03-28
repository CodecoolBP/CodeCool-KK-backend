package com.codecool.cckk.repository;

import com.codecool.cckk.model.CckkUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<CckkUser, Long> {

    @Query("SELECT u FROM CckkUser u WHERE u.email = :emailaddress")
    CckkUser findUserByEmail(@Param("emailaddress") String emailaddress);

    @Query("SELECT u FROM CckkUser u JOIN u.cards c WHERE c.cardNumber = :inputcard")
    CckkUser findUserByCardNumber(@Param("inputcard") Long cardNumber);
}
