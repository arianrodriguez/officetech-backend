package com.officetech.officetech.API.usersauth.domain.model.aggregates;

/*
* User aggregate
* <p>The User class is an aggregate root that represents a user.
* It is responsible for handling the CreateUserCommand command.</p>
* */

import com.officetech.officetech.API.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.officetech.officetech.API.usersauth.domain.model.commands.CreateUserAuthCommand;
import com.officetech.officetech.API.usersauth.domain.model.commands.GetUserAuthCommand;
import com.officetech.officetech.API.usersauth.domain.model.queries.GetUserByEmailQuery;
import com.officetech.officetech.API.usersauth.domain.model.valueobjects.*;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class) // it is used to automatically populate the createdAt and updatedAt fields
public class UserAuth extends AuditableAbstractAggregateRoot<UserAuth> {

    @Embedded
    private FirstName firstName;

    @Embedded
    private LastName lastName;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Role role;

    protected UserAuth() {}
    public UserAuth(String firstName, String lastName, String email, String password, String role) {
        this.firstName = new FirstName(firstName);
        this.lastName = new LastName(lastName);
        this.email = new Email(email);
        this.password = new Password(password);
        this.role = new Role(role);
        this.setCreatedAt();
        this.setUpdatedAt();
    }
    public UserAuth(CreateUserAuthCommand command) {
        System.out.println("UserAuth: creating new user entity aggregate");
        this.firstName = new FirstName(command.firstName());
        this.lastName = new LastName(command.lastName());
        this.email = new Email(command.email());
        this.password = new Password(command.password());
        this.role = new Role(command.role());
        this.setCreatedAt();
        this.setUpdatedAt();
    }
    public UserAuth(GetUserByEmailQuery query) {
        this.email = query.email();
    }

    public String getFirstName() {return firstName.getFirstName();}
    public String getLastName() {return lastName.getLastName();}
    public String getRole() {return role.getRole();}
    public String getEmail() {return email.getEmail();}
    public String getPassword() {return password.getPassword();}
    public Email getEmailObject() {return email;}
}
