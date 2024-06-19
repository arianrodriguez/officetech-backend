package com.officetech.officetech.API.profile.infrastructure.persistence.jpa.repositories;

import com.officetech.officetech.API.profile.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Optional<Profile> findByEmail(String email);
}
