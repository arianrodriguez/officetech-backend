package com.officetech.officetech.API.services.interfaces.rest;

import com.officetech.officetech.API.services.domain.model.aggregates.ServiceOfficeTech;
import com.officetech.officetech.API.services.domain.model.queries.GetServicesOfficeTechByCompanyIdQuery;
import com.officetech.officetech.API.services.domain.services.ServiceOfficeTechCommandService;
import com.officetech.officetech.API.services.domain.services.ServiceOfficeTechQueryService;
import com.officetech.officetech.API.services.interfaces.rest.resources.CreateNewServiceOfficeTechResource;
import com.officetech.officetech.API.services.interfaces.rest.resources.EditServiceOfficeTechResource;
import com.officetech.officetech.API.services.interfaces.rest.transform.CreateServiceOfficeTechCommandFromResourceAssembler;
import com.officetech.officetech.API.services.interfaces.rest.transform.EditServiceOfficeTechCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/services", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Services", description = "Services Management Endpoints")
public class ServicesOfficeTechController {
    private final ServiceOfficeTechCommandService serviceOfficeTechCommandService;
    private final ServiceOfficeTechQueryService serviceOfficeTechQueryService;
    public ServicesOfficeTechController(ServiceOfficeTechCommandService serviceOfficeTechCommandService, ServiceOfficeTechQueryService serviceOfficeTechQueryService) {
        this.serviceOfficeTechCommandService = serviceOfficeTechCommandService;
        this.serviceOfficeTechQueryService = serviceOfficeTechQueryService;
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

    /**
     * PUT api/v1/services/{serviceId}
     * endpoint: http://localhost:8080/api/v1/services/serviceId?comment=' '&rating=' '
     * Endpoint that updates a service the company edit a comment or rating of his services history
     * @param serviceId id of the service to update
     * @param comment comment new comment to add to the service
     * @param rating rating new rating to add to the service
     * */
    @PutMapping("/{serviceId}")
    public ResponseEntity<Response> updateService(@PathVariable Long serviceId, @Param("comment") String comment, @Param("rating") Integer rating) {
        var resource = new EditServiceOfficeTechResource(comment, rating, serviceId);
        var command = EditServiceOfficeTechCommandFromResourceAssembler.toCommandFromResource(resource);

        boolean result = serviceOfficeTechCommandService.handle(command);
        if (!result) { // if the service was not updated
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     * PUT api/v1/services/{serviceId}?status=' '
     * endpoint: http://localhost:8080/api/v1/services/serviceId?status=' '
     * Endpoint that updates a service the company edit the status of his services history
     * @param serviceId id of the service to update
     * @param status status new status to add to the service
     * */
    @PutMapping("/change-status/{serviceId}")
    public ResponseEntity<Response> updateServiceStatus(@PathVariable Long serviceId, @Param("status") String status) {
        boolean result = serviceOfficeTechCommandService.handle(serviceId, status);
        if (!result) { // if the service was not updated
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     * GET api/v1/services/company/active/{companyId}
     * Endpoint to obtain the services of a company that requested them with status service IN PROGRESS, ACTIVE
     * @param companyId id of the company to get the services
     *
    * */
    @GetMapping("/company/active/{companyId}")
    public List<ServiceOfficeTech> getActiveServicesCompany(@PathVariable Long companyId) {
        var query = new GetServicesOfficeTechByCompanyIdQuery(companyId);
        return serviceOfficeTechQueryService.handle(query, 1);
    }

    /**
     * GET api/v1/services/company/active/{companyId}
     * Endpoint to obtain the services of a company that requested them with status service CANCELLED OR COMPLETED
     * @param companyId id of the company to get the services
     *
     * */
    @GetMapping("/company/completed/{companyId}")
    public List<ServiceOfficeTech> getCompletedServicesCompany(@PathVariable Long companyId) {
        var query = new GetServicesOfficeTechByCompanyIdQuery(companyId);
        return serviceOfficeTechQueryService.handle(query, 2);
    }
}
