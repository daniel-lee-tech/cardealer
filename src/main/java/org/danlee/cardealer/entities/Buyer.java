package org.danlee.cardealer.entities;

import java.util.UUID;

public class Buyer {
    private UUID id;
    private String fullName;
    private String email;
    private String fullAddress;

    public Buyer() {
        this.id = UUID.randomUUID();
    }

    public Buyer(String fullName, String email, String fullAddress) {
        this.fullName = fullName;
        this.email = email;
        this.fullAddress = fullAddress;
        this.id = UUID.randomUUID();
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
}
