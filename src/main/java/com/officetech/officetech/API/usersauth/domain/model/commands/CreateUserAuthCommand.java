package com.officetech.officetech.API.usersauth.domain.model.commands;
/*
* Command to create a UserAuth.
* <p>The CreateUserAuthCommand record encapsulates the data required to create a UserAuth.
* It validates that the email, password, and role are not null or empty.</p>
* */
public record CreateUserAuthCommand(String email, String password, String role) {
    public CreateUserAuthCommand {
        System.out.println("CreateUserAuthCommand: creating new command");
        // regex to validate email
        if (email == null || email.isBlank() || !email.matches("^(.+)@(.+)$")) {
            throw new IllegalArgumentException("email cannot be null or empty and must be a valid email");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("role cannot be null or empty");
        }
    }

}
