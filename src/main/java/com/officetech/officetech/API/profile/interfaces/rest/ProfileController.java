package com.officetech.officetech.API.profile.interfaces.rest;

import com.officetech.officetech.API.profile.domain.model.aggregates.Profile;
import com.officetech.officetech.API.profile.domain.model.commands.UpdateProfileCommand;
import com.officetech.officetech.API.profile.interfaces.rest.resources.ProfileResource;
import com.officetech.officetech.API.profile.interfaces.rest.transform.ProfileCommandFromResourceAssembler;
import com.officetech.officetech.API.profile.domain.services.ProfileCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileCommandService profileCommandService;
    private final ProfileCommandFromResourceAssembler assembler;

    public ProfileController(ProfileCommandService profileCommandService, ProfileCommandFromResourceAssembler assembler) {
        this.profileCommandService = profileCommandService;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody ProfileResource resource) {
        UpdateProfileCommand command = assembler.toCommandFromResource(resource);
        Optional<Profile> newProfile = profileCommandService.handle(command);
        return newProfile.map(source-> new ResponseEntity<>(source, CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }


}