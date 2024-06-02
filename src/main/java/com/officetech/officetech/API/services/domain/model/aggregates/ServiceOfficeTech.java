package com.officetech.officetech.API.services.domain.model.aggregates;

import com.officetech.officetech.API.services.domain.model.commands.CreateNewServiceOfficeTechCommand;
import com.officetech.officetech.API.services.domain.model.valueobjects.*;
import com.officetech.officetech.API.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class) // it is used to automatically populate the createdAt and updatedAt fields
public class ServiceOfficeTech extends AuditableAbstractAggregateRoot<ServiceOfficeTech> {
    @Embedded
    private Title title;
    @Embedded
    private Description description;
    @Embedded
    private Pricing estimatePricing;
    @Embedded
    private Date date;
    @Embedded
    private Status statusValue;

    private String status;
    @Embedded
    private Comment comment;
    private Long companyId;
    private Long technicianId;
    private Integer rating;

    public ServiceOfficeTech() {
        this.title = new Title();
        this.description = new Description();
        this.estimatePricing = new Pricing();
        this.date = new Date();
        this.comment = new Comment();
        this.status = "In Progress";
        this.statusValue = Status.IN_PROGRESS;
        this.companyId = 0L;
        this.technicianId = 0L;
        this.rating = 0;
        this.setCreatedAt();
        this.setUpdatedAt();
    }

    public ServiceOfficeTech(CreateNewServiceOfficeTechCommand command) {
        this.title = new Title(command.title());
        this.description = new Description(command.description());
        this.estimatePricing = new Pricing(command.estimatePricing());
        this.date = new Date(command.date());
        this.status = "In Progress";
        this.statusValue = Status.IN_PROGRESS;
        this.comment = new Comment(command.comment());
        this.companyId = command.companyId();
        this.technicianId = command.technicianId();
        this.rating = command.rating();
        this.setCreatedAt();
        this.setUpdatedAt();
    }

    public boolean isCompleted() {
        return this.statusValue == Status.COMPLETED;
    }
    public boolean isInProgress() {
        return this.statusValue == Status.IN_PROGRESS;
    }
    public boolean isCancelled() {
        return this.statusValue == Status.CANCELLED;
    }
    public boolean isActive() {
        return this.statusValue == Status.ACTIVE;
    }
}
