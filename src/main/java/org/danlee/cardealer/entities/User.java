package org.danlee.cardealer.entities;

import org.danlee.cardealer.enums.UserRoles;
import org.danlee.cardealer.utils.PasswordUtils;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

public class User {
    private UUID id;
    private String fullName;
    private String email;
    private String fullAddress;
    private ArrayList<UserRoles> roles;
    private String passwordDigest;

    public User() {
        this.id = UUID.randomUUID();
    }

    public User(String fullName, String email, String fullAddress, ArrayList<UserRoles> roles, String plainTextPassword) {
        this.fullName = fullName;
        this.email = email;
        this.fullAddress = fullAddress;
        this.id = UUID.randomUUID();
        this.roles = roles;
        this.passwordDigest = PasswordUtils.createPasswordDigest(plainTextPassword);
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

    public ArrayList<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<UserRoles> roles) {
        this.roles = roles;
    }

    public String getPasswordDigest() {
        return passwordDigest;
    }

    public void setPasswordDigest(String plainTextPassword) {

        this.passwordDigest = PasswordUtils.createPasswordDigest(plainTextPassword);
    }
}
