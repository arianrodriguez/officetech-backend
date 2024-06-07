package com.officetech.officetech.API.usersauth.domain.services;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;

import java.util.Optional;

public interface SkillCommandService {
    Optional<UserAuth> addSkillToUser(Long userId, Long skillId);
    Optional<UserAuth> removeSkillFromUser(Long userId, Long skillId);
}