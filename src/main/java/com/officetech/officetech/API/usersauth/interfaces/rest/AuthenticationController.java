package com.officetech.officetech.API.usersauth.interfaces.rest;

/*
* REST controller for managing authentication.
* <p>This controller provides endpoints for authenticating users.
* It handles HTTP requests and delegates to the appropriate services.</p>
* */

import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.services.UserAuthCommandService;
import com.officetech.officetech.API.usersauth.interfaces.rest.resources.CreateUserAuthResource;
import com.officetech.officetech.API.usersauth.interfaces.rest.resources.UserAuthResource;
import com.officetech.officetech.API.usersauth.interfaces.rest.transform.CreateUserAuthCommandFromResourceAssembler;
import com.officetech.officetech.API.usersauth.interfaces.rest.transform.UserAuthResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final UserAuthCommandService userAuthCommandService;

    public AuthenticationController(UserAuthCommandService userAuthCommandService) {
        this.userAuthCommandService = userAuthCommandService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserAuthResource> registerNewUser(@RequestBody CreateUserAuthResource resource) {
        System.out.println("Registering new user");
        Optional<UserAuth> user = userAuthCommandService.handle(CreateUserAuthCommandFromResourceAssembler.toCommandFromResource(resource));

        return user.map(source -> new ResponseEntity<>(UserAuthResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
