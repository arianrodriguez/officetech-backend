package com.officetech.officetech.API.usersauth.application.internal.queryservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.Skill;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.services.SkillQueryService;
import com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa.UserAuthRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class SkillQueryServiceImpl implements SkillQueryService {

    private final UserAuthRepository userAuthRepository;

    @Autowired
    public SkillQueryServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public Optional<Set<Skill>> getSkillsByUserId(Long userId) {
        Optional<UserAuth> userOpt = userAuthRepository.findByIdWithSkills(userId);
        return userOpt.map(UserAuth::getSkills);
    }
}
