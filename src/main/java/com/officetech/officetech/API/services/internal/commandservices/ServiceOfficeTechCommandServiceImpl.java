package com.officetech.officetech.API.services.internal.commandservices;

import com.officetech.officetech.API.services.domain.model.aggregates.ServiceOfficeTech;
import com.officetech.officetech.API.services.domain.model.commands.CreateNewServiceOfficeTechCommand;
import com.officetech.officetech.API.services.domain.services.ServiceOfficeTechCommandService;
import com.officetech.officetech.API.services.infrastructure.persistence.repositories.ServiceOfficeTechRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceOfficeTechCommandServiceImpl implements ServiceOfficeTechCommandService {
    ServiceOfficeTechRepository serviceOfficeTechRepository;
    public ServiceOfficeTechCommandServiceImpl(ServiceOfficeTechRepository serviceOfficeTechRepository) {
        this.serviceOfficeTechRepository = serviceOfficeTechRepository;
    }

    @Override
    public boolean handle(CreateNewServiceOfficeTechCommand command) {
        ServiceOfficeTech serviceOfficeTech = new ServiceOfficeTech(command);
        try {
            this.serviceOfficeTechRepository.save(serviceOfficeTech);
            return true;
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
