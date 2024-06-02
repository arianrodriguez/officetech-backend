package com.officetech.officetech.API.services.domain.services;

import com.officetech.officetech.API.services.domain.model.aggregates.ServiceOfficeTech;
import com.officetech.officetech.API.services.domain.model.queries.GetServicesOfficeTechByCompanyIdQuery;

import java.util.List;

public interface ServiceOfficeTechQueryService {
    List<ServiceOfficeTech> handle(GetServicesOfficeTechByCompanyIdQuery query, Integer option);
}
