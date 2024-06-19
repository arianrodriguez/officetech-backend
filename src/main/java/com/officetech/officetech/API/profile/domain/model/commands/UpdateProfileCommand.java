package com.officetech.officetech.API.profile.domain.model.commands;

public record UpdateProfileCommand(Long id, String firstName, String lastName, String email, String phone, String password) {
    public UpdateProfileCommand {
        if (id == null) {
            throw new IllegalArgumentException("id is required");
        }
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstName is required");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastName is required");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email is required");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("phone is required");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password is required");
        }
    }
}
