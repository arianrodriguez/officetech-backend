package com.officetech.officetech.API.services.domain.services;

import com.officetech.officetech.API.services.domain.model.aggregates.ServiceOfficeTech;
import com.officetech.officetech.API.services.domain.model.queries.GetServicesOfficeTechByCompanyIdQuery;
import com.officetech.officetech.API.services.domain.model.queries.GetServicesOfficeTechByTechnicianId;

import java.util.List;

public interface ServiceOfficeTechQueryService {
    List<ServiceOfficeTech> handle(GetServicesOfficeTechByCompanyIdQuery query, Integer option);
    List<ServiceOfficeTech> handle(GetServicesOfficeTechByTechnicianId query, Integer option);
}
