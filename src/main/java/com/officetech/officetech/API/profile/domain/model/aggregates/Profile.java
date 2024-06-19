package com.officetech.officetech.API.profile.domain.model.aggregates;

import com.officetech.officetech.API.profile.domain.model.commands.UpdateProfileCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Profile extends AbstractAggregateRoot<Profile> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(nullable = false)
    @Getter @Setter
    private String firstName;

    @Column(nullable = false)
    @Getter @Setter
    private String lastName;

    @Column(nullable = false)
    @Getter @Setter
    private String email;

    @Column(nullable = false)
    @Getter @Setter
    private String phone;

    @Column(nullable = false)
    @Getter @Setter
    private String password;

    protected Profile() {}

    public Profile(UpdateProfileCommand command) {
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.email = command.email();
        this.phone = command.phone();
        this.password = command.password();
    }

}
