package com.codecool.cckk.repository;

import com.codecool.cckk.model.CckkUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<CckkUser, Long> {

    @Query("SELECT u FROM CckkUser u WHERE u.email = :emailaddress")
    CckkUser findUserById(@Param("emailaddress") String emailaddress);
}
