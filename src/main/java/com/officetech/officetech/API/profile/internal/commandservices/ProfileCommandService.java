package com.officetech.officetech.API.profile.internal.commandservices;


import com.officetech.officetech.API.profile.domain.model.aggregates.Profile;
import com.officetech.officetech.API.profile.domain.model.commands.UpdateProfileCommand;
import com.officetech.officetech.API.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProfileCommandService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileCommandService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    public Profile updateProfile(UpdateProfileCommand command) {
        Profile profile = profileRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        profile.setFirstName(command.firstName());
        profile.setLastName(command.lastName());
        profile.setEmail(command.email());
        profile.setPhone(command.phone());
        profile.setPassword(command.password());
        return profileRepository.save(profile);
    }
}