package com.officetech.officetech.API.usersauth.application.internal.queryservices;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.queries.GetUserByEmailQuery;
import com.officetech.officetech.API.usersauth.domain.services.UserAuthQueryService;
import com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa.UserAuthRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthQueryServiceImpl implements UserAuthQueryService {
    UserAuthRepository userAuthRepository;
    public UserAuthQueryServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public boolean handle(GetUserByEmailQuery query) {
        Optional<UserAuth> getUserAuth = Optional.empty();
        try {
            var userAuth = new UserAuth(query);
            getUserAuth = userAuthRepository.findUserAuthByEmail(userAuth.getEmailObject());
        }catch(Exception e) {
            System.out.println("UserAuthCommandServiceImpl: Error while saving user entity" + e);

        }

        return getUserAuth.isEmpty();
    }
}
