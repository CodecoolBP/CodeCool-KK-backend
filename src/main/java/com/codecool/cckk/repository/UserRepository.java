package com.codecool.cckk.repository;

import com.codecool.cckk.model.CckkUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CckkUser, Long> {
}
