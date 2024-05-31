package com.officetech.officetech.API.usersauth.application.internal.queryservices;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.queries.AuthUserQuery;
import com.officetech.officetech.API.usersauth.domain.model.queries.GetUserByEmailQuery;
import com.officetech.officetech.API.usersauth.domain.services.UserAuthQueryService;
import com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa.UserAuthRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

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

    @Override
    public boolean handle(AuthUserQuery query) {
        try {
            // manage transactions with repository
            List<UserAuth> users = userAuthRepository.findAll();
            return users.stream().anyMatch(user -> user.getEmail().equals(query.email().getEmail()) && user.getPassword().equals(query.password().getPassword()));
        }catch(Exception e) {
            System.out.println("UserAuthCommandServiceImpl: Error while saving user entity" + e);
            return false;
        }
    }
}
