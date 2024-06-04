package com.officetech.officetech.API.services.infrastructure.persistence.repositories;

import com.officetech.officetech.API.services.domain.model.aggregates.ServiceOfficeTech;
import com.officetech.officetech.API.services.domain.model.entities.UserEntity;
import com.officetech.officetech.API.services.interfaces.rest.resources.UserResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceOfficeTechRepository extends JpaRepository<ServiceOfficeTech, Long> {
    List<ServiceOfficeTech> findAllByCompanyId(Long companyId);

    @Query("SELECT s FROM ServiceOfficeTech s WHERE s.companyId = ?1 AND s.status IN ('In Progress', 'Active')")
    List<ServiceOfficeTech> findAllByCompanyIdWhereStatusIsInProgress(Long companyId);

    @Query("SELECT s FROM ServiceOfficeTech s WHERE s.companyId = ?1 AND s.status IN ('Cancelled', 'Completed')")
    List<ServiceOfficeTech> findAllByCompanyIdWhereStatusIsInCompleted(Long companyId);

    @Query("SELECT s FROM ServiceOfficeTech s WHERE s.technicianId = ?1 AND s.status IN ('In Progress', 'Active')")
    List<ServiceOfficeTech> findAllByTechnicianIdWhereStatusIsInProgress(Long technicianId);

    @Query("SELECT s FROM ServiceOfficeTech s WHERE s.technicianId = ?1 AND s.status IN ('Cancelled', 'Completed')")
    List<ServiceOfficeTech> findAllByTechnicianIdWhereStatusIsInCompleted(Long technicianId);

    @Query("SELECT u FROM UserEntity u WHERE u.id = ?1")
    Optional<UserEntity> findUserById(Long userId);

    @Query("SELECT u from UserEntity u WHERE u.type_user = 'technician'")
    List<UserEntity> findAllTechnicians();

    Optional<ServiceOfficeTech> findServiceOfficeTechById(Long id);
}
