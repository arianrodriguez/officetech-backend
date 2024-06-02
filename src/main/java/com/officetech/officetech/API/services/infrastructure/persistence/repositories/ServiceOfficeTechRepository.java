package com.officetech.officetech.API.services.infrastructure.persistence.repositories;

import com.officetech.officetech.API.services.domain.model.aggregates.ServiceOfficeTech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
