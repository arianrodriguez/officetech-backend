package com.officetech.officetech.API.services.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
public class UserEntity {
    @Getter
    String type_user;
    @Getter
    String name;
    @Getter
    String email;
    @Getter
    String password;

    @Id
    @Getter
    private Long id;

    public UserEntity() {
        this.type_user = Strings.EMPTY;
        this.name = Strings.EMPTY;
        this.email = Strings.EMPTY;
        this.password = Strings.EMPTY;
    }

    public UserEntity(String type_user, String name, String email, String password) {
        this.type_user = type_user;
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
