package com.officetech.officetech.API.services.infrastructure.persistence.repositories;

import com.officetech.officetech.API.services.domain.model.aggregates.ServiceOfficeTech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOfficeTechRepository extends JpaRepository<ServiceOfficeTech, Long> {
}
