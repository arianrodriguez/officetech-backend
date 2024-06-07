package com.officetech.officetech.API.profile.interfaces.rest.resources;

public record ProfileResource(Long id, String firstName, String lastName, String email, String phone, String password) {
    public ProfileResource {
        if(id == null){
            throw new IllegalArgumentException("id is required");
        }
        if (firstName == null) {
            throw new IllegalArgumentException("firstName is required");
        }
        if (lastName == null) {
            throw new IllegalArgumentException("lastName is required");
        }
        if (email == null) {
            throw new IllegalArgumentException("email is required");
        }
        if (phone == null) {
            throw new IllegalArgumentException("phone is required");
        }
        if (password == null) {
            throw new IllegalArgumentException("password is required");
        }
    }
}
