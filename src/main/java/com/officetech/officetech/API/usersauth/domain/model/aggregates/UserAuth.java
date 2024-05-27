package com.officetech.officetech.API.usersauth.domain.model.aggregates;

/*
* User aggregate
* <p>The User class is an aggregate root that represents a user.
* It is responsible for handling the CreateUserCommand command.</p>
* */

import com.officetech.officetech.API.usersauth.domain.model.commands.CreateUserAuthCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class) // it is used to automatically populate the createdAt and updatedAt fields
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String email;

    @Column(nullable = false)
    @Getter
    private String password;

    @Column(nullable = false)
    @Getter
    private String role;

    protected UserAuth() {}
    public UserAuth(CreateUserAuthCommand command) {
        System.out.println("UserAuth: creating new user entity aggregate");
        this.email = command.email();
        this.password = command.password();
        this.role = command.role();
    }
}
