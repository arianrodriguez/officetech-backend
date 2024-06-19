package com.officetech.officetech.API.usersauth.domain.services;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.Skill;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.commands.CreateSkillCommand;

import java.util.Optional;

public interface SkillCommandService {
    Optional<Skill> handle(CreateSkillCommand command);
    Optional<UserAuth> removeSkillFromUser(Long userId, Long skillId);
}