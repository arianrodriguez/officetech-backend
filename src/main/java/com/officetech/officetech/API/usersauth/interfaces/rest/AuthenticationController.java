package com.officetech.officetech.API.usersauth.interfaces.rest;

/*
* REST controller for managing authentication.
* <p>This controller provides endpoints for authenticating users.
* It handles HTTP requests and delegates to the appropriate services.</p>
* */

import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.queries.AuthUserQuery;
import com.officetech.officetech.API.usersauth.domain.model.queries.GetUserByEmailQuery;
import com.officetech.officetech.API.usersauth.domain.model.valueobjects.Email;
import com.officetech.officetech.API.usersauth.domain.model.valueobjects.Password;
import com.officetech.officetech.API.usersauth.domain.services.UserAuthCommandService;
import com.officetech.officetech.API.usersauth.domain.services.UserAuthQueryService;
import com.officetech.officetech.API.usersauth.interfaces.rest.resources.CreateUserAuthResource;
import com.officetech.officetech.API.usersauth.interfaces.rest.resources.UserAuthResource;
import com.officetech.officetech.API.usersauth.interfaces.rest.transform.CreateUserAuthCommandFromResourceAssembler;
import com.officetech.officetech.API.usersauth.interfaces.rest.transform.UserAuthResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Management Endpoints")
public class AuthenticationController {
    private final UserAuthCommandService userAuthCommandService;
    private final UserAuthQueryService userAuthQueryService;

    public AuthenticationController(UserAuthCommandService userAuthCommandService, UserAuthQueryService userAuthQueryService) {
        this.userAuthCommandService = userAuthCommandService;
        this.userAuthQueryService = userAuthQueryService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserAuthResource> registerNewUser(@RequestBody CreateUserAuthResource resource) {
        System.out.println("Registering new user");
        Optional<UserAuth> user = userAuthCommandService.handle(CreateUserAuthCommandFromResourceAssembler.toCommandFromResource(resource));

        return user.map(source -> new ResponseEntity<>(UserAuthResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/login")
    public ResponseEntity<UserAuthResource> existsUser(String email, String password) {
        System.out.println("Logging in user");
        GetUserByEmailQuery query = new GetUserByEmailQuery(new Email(email));
        boolean user = userAuthQueryService.handle(query);
        if(user) {
            System.out.println("User not found");
            return ResponseEntity.badRequest().build();
        }

        AuthUserQuery authUserQuery = new AuthUserQuery(new Email(email), new Password(password));
        boolean resultMatch = userAuthQueryService.handle(authUserQuery);
        if(!resultMatch) {
            System.out.println("Password incorrect");
            return ResponseEntity.badRequest().build();
        }

        System.out.println("Logged!");
        return ResponseEntity.ok().build();
    }

}
