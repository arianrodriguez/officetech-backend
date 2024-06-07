package com.officetech.officetech.API.usersauth.domain.model.aggregates;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "skills")
    private Set<UserAuth> users = new HashSet<>();

    protected Skill() {}

    public Skill(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<UserAuth> getUsers() {
        return users;
    }
}
