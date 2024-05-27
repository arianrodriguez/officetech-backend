package com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    boolean existsById(Long id);
    //UserAuth findByUserId(String userId);
}
