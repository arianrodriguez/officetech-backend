package com.officetech.officetech.API.services.internal.queryservices;

import com.officetech.officetech.API.services.domain.model.aggregates.ServiceOfficeTech;
import com.officetech.officetech.API.services.domain.model.queries.GetServicesOfficeTechByCompanyIdQuery;
import com.officetech.officetech.API.services.domain.model.queries.GetServicesOfficeTechByTechnicianId;
import com.officetech.officetech.API.services.domain.services.ServiceOfficeTechQueryService;
import com.officetech.officetech.API.services.infrastructure.persistence.repositories.ServiceOfficeTechRepository;
import com.officetech.officetech.API.services.internal.commandservices.ServiceOfficeTechCommandServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOfficeTechQueryServiceImpl implements ServiceOfficeTechQueryService {
    ServiceOfficeTechRepository serviceOfficeTechRepository;
    public ServiceOfficeTechQueryServiceImpl(ServiceOfficeTechRepository serviceOfficeTechRepository) {
        this.serviceOfficeTechRepository = serviceOfficeTechRepository;
    }

    @Override
    public List<ServiceOfficeTech> handle(GetServicesOfficeTechByCompanyIdQuery query, Integer option) {
        try {
            if(option == 1)
                return serviceOfficeTechRepository.findAllByCompanyIdWhereStatusIsInProgress(query.companyId());
            else
                return serviceOfficeTechRepository.findAllByCompanyIdWhereStatusIsInCompleted(query.companyId());
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public List<ServiceOfficeTech> handle(GetServicesOfficeTechByTechnicianId query, Integer option) {
        try {
            if(option == 1)
                return serviceOfficeTechRepository.findAllByTechnicianIdWhereStatusIsInProgress(query.technicianId());
            else
                return serviceOfficeTechRepository.findAllByTechnicianIdWhereStatusIsInCompleted(query.technicianId());
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return List.of();
        }
    }
}
