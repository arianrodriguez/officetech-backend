package com.officetech.officetech.API.usersauth.domain.services;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.Skill;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SkillQueryService {
    List<Skill> getSkillsByUserId(Long userId);
}