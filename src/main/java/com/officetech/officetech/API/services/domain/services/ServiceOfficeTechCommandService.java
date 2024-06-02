package com.officetech.officetech.API.services.domain.services;

import com.officetech.officetech.API.services.domain.model.commands.CreateNewServiceOfficeTechCommand;

import java.util.Optional;

public interface ServiceOfficeTechCommandService {
    boolean handle(CreateNewServiceOfficeTechCommand command);
}
