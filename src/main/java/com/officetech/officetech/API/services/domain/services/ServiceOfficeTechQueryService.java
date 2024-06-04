package com.officetech.officetech.API.services.domain.services;

import com.officetech.officetech.API.services.domain.model.aggregates.ServiceOfficeTech;
import com.officetech.officetech.API.services.domain.model.entities.UserEntity;
import com.officetech.officetech.API.services.domain.model.queries.*;
import com.officetech.officetech.API.services.interfaces.rest.resources.UserResource;

import java.util.List;
import java.util.Optional;

public interface ServiceOfficeTechQueryService {
    List<ServiceOfficeTech> handle(GetServicesOfficeTechByCompanyIdQuery query, Integer option);
    List<ServiceOfficeTech> handle(GetServicesOfficeTechByTechnicianId query, Integer option);
    Optional<UserEntity> handle(GetUserByIdQuery query);

    List<UserEntity> handle(GetUsersTechnicianQuery query);

    Optional<ServiceOfficeTech> handle(GetServicesByIdQuery query);
}
