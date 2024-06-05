package com.officetech.officetech.API.services.interfaces.rest.transform;

import com.officetech.officetech.API.services.domain.model.entities.UserEntity;
import com.officetech.officetech.API.services.interfaces.rest.resources.UserResource;

public class CreateUserResourceFromEntity {
    public static UserResource toResourceFromEntity(UserEntity entity) {
        return new UserResource(entity.getId(), entity.getType_user(), entity.getName(), entity.getEmail());
    }
}
