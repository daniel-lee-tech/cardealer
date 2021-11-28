package org.danlee.cardealer.entities;

import org.danlee.cardealer.enums.UserRoles;

import java.util.UUID;

public class User {
    private UUID id;
    private String fullName;
    private String email;
    private String fullAddress;
    private UserRoles role;

    public User() {
        this.id = UUID.randomUUID();
    }

    public User(String fullName, String email, String fullAddress, UserRoles role) {
        this.fullName = fullName;
        this.email = email;
        this.fullAddress = fullAddress;
        this.id = UUID.randomUUID();
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
}
