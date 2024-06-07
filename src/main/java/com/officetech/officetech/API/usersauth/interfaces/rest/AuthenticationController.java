package com.officetech.officetech.API.usersauth.interfaces.rest;

/*
* REST controller for managing authentication.
* <p>This controller provides endpoints for authenticating users.
* It handles HTTP requests and delegates to the appropriate services.</p>
* */

import com.officetech.officetech.API.usersauth.application.internal.commandservices.SkillCommandServiceImpl;
import com.officetech.officetech.API.usersauth.application.internal.queryservices.SkillQueryServiceImpl;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.queries.AuthUserQuery;
import com.officetech.officetech.API.usersauth.domain.model.queries.GetUserByEmailQuery;
import com.officetech.officetech.API.usersauth.domain.model.valueobjects.Email;
import com.officetech.officetech.API.usersauth.domain.model.valueobjects.Password;
import com.officetech.officetech.API.usersauth.domain.services.UserAuthCommandService;
import com.officetech.officetech.API.usersauth.domain.services.UserAuthQueryService;
import com.officetech.officetech.API.usersauth.interfaces.rest.resources.*;
import com.officetech.officetech.API.usersauth.interfaces.rest.transform.CreateUserAuthCommandFromResourceAssembler;
import com.officetech.officetech.API.usersauth.interfaces.rest.transform.UserAuthResourceFromEntityAssembler;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.Skill;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Management Endpoints")
public class AuthenticationController {
    private final UserAuthCommandService userAuthCommandService;
    private final UserAuthQueryService userAuthQueryService;
    private final SkillQueryServiceImpl skillQueryService;
    @Autowired
    private SkillCommandServiceImpl skillCommandService;


    public AuthenticationController(UserAuthCommandService userAuthCommandService, UserAuthQueryService userAuthQueryService, SkillQueryServiceImpl skillQueryService) {
        this.userAuthCommandService = userAuthCommandService;
        this.userAuthQueryService = userAuthQueryService;
        this.skillQueryService = skillQueryService;
    }

    @PostMapping("/register")
    public ValidateUserAuthResource registerNewUser(@RequestBody CreateUserAuthResource resource) {
        // to verify if the user's email already exists in the database
        GetUserByEmailQuery query = new GetUserByEmailQuery(new Email(resource.email()));
        var userExists = userAuthQueryService.handle(query);
        if(userExists.isPresent()) {
            System.out.println("User already exists");
            // return a body like a : {"status_code: 400", "message": "User already exists}
            return new ValidateUserAuthResource(400, "User already exists");

        }
        else {
            System.out.println("Registering new user");
            var command = CreateUserAuthCommandFromResourceAssembler.toCommandFromResource(resource);
            var validation = new ValidateUserAuthCommand(command);
            var result = validation.validate();
            if(!Objects.equals(result, "")) {
                System.out.println("Error while validating user entity");
                return new ValidateUserAuthResource(400, result);
            }

            Optional<UserAuth> user = userAuthCommandService.handle(command);
            if(user.isEmpty()) {
                System.out.println("Error while saving user entity");
                // return a body like a : {"status_code: 500", "message": "Error while saving user entity}
                return new ValidateUserAuthResource(500, "Error while saving user entity");
            }
            return new ValidateUserAuthResource(202, "User created successfully");
        }
    }

    @GetMapping("/login")
    public GetUserAuthResource existsUser(String email, String password) {
        System.out.println("Logging in user");
        GetUserByEmailQuery query = new GetUserByEmailQuery(new Email(email));
        var user = userAuthQueryService.handle(query);
        if(user.isEmpty()) {
            System.out.println("User not found");
            return new GetUserAuthResource(404, "User not found", null);
        }

        AuthUserQuery authUserQuery = new AuthUserQuery(new Email(email), new Password(password));
        boolean resultMatch = userAuthQueryService.handle(authUserQuery);
        if(!resultMatch) {
            System.out.println("Password incorrect");
            return new GetUserAuthResource(400, "Password incorrect", null);
        }

        System.out.println("Logged!");

        return new GetUserAuthResource(202, "Logged", UserAuthResourceFromEntityAssembler.toResourceFromEntity(user.get()));

    }
    @GetMapping("/{userId}/skills")
    public ResponseEntity<Set<Skill>> getSkillsByUserId(@PathVariable Long userId) {
        Optional<Set<Skill>> skills = skillQueryService.getSkillsByUserId(userId);
        return skills.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/{userId}/skills/{skillId}")
    public ResponseEntity<UserAuth> addSkillToUser(@PathVariable Long userId, @PathVariable Long skillId) {
        Optional<UserAuth> user = skillCommandService.addSkillToUser(userId, skillId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{userId}/skills/{skillId}")
    public ResponseEntity<UserAuth> removeSkillFromUser(@PathVariable Long userId, @PathVariable Long skillId) {
        Optional<UserAuth> user = skillCommandService.removeSkillFromUser(userId, skillId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
