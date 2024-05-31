package com.officetech.officetech.API.usersauth.domain.services;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.queries.AuthUserQuery;
import com.officetech.officetech.API.usersauth.domain.model.queries.GetUserByEmailQuery;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserAuthQueryService {
    boolean handle(GetUserByEmailQuery query);
    boolean handle(AuthUserQuery query);
}
