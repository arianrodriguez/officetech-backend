package com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.valueobjects.Email;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    boolean existsById(Long id);
    //UserAuth findByUserId(String userId);
    Optional<UserAuth> findUserAuthByEmail(Email email);
    List<UserAuth> findAll();
}
