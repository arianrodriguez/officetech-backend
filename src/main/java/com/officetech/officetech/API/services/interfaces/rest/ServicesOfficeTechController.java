package com.officetech.officetech.API.services.interfaces.rest;

import com.officetech.officetech.API.services.domain.services.ServiceOfficeTechCommandService;
import com.officetech.officetech.API.services.interfaces.rest.resources.CreateNewServiceOfficeTechResource;
import com.officetech.officetech.API.services.interfaces.rest.transform.CreateServiceOfficeTechCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/services", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Services", description = "Services Management Endpoints")
public class ServicesOfficeTechController {
    private final ServiceOfficeTechCommandService serviceOfficeTechCommandService;
    public ServicesOfficeTechController(ServiceOfficeTechCommandService serviceOfficeTechCommandService) {
        this.serviceOfficeTechCommandService = serviceOfficeTechCommandService;
    }

    /**
    * POST api/v1/services
    * Endpoint that creates a service, the company request a new tech service
    * @param resource the resource with the information to create the service
    * @return the response of the service creation
    * */
    @PostMapping
    public ResponseEntity<Response> createService(@RequestBody CreateNewServiceOfficeTechResource resource) {
        var command = CreateServiceOfficeTechCommandFromResourceAssembler.toCommandFromResource(resource);
        boolean result = serviceOfficeTechCommandService.handle(command);
        if (!result) { // if the service was not created
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
}
