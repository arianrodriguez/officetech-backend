package com.officetech.officetech.API.profile.domain.services;

import com.officetech.officetech.API.profile.domain.model.aggregates.Profile;
import com.officetech.officetech.API.profile.domain.model.commands.UpdateProfileCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProfileCommandService {
    Optional<Profile> handle(UpdateProfileCommand command);
}