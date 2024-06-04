package com.officetech.officetech.API.usersauth.domain.model.commands;
/*
* Command to create a UserAuth.
* <p>The CreateUserAuthCommand record encapsulates the data required to create a UserAuth.
* It validates that the email, password, and role are not null or empty.</p>
* */
public record CreateUserAuthCommand(String firstName, String lastName, String email, String password, String role) {
    public CreateUserAuthCommand {
        System.out.println("CreateUserAuthCommand: creating new command");
        if (firstName == null || firstName.isBlank() || !firstName.matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("First name cannot be null or empty and must contain only letters");
        }
        if (lastName == null || lastName.isBlank() || !lastName.matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("Last name cannot be null or empty and must contain only letters");
        }

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
