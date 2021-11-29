package org.danlee.cardealer.dto;

import org.danlee.cardealer.entities.User;

public class SignupDTO extends BaseDTO {
    private User user;
    private String plainTextPassword;
    private String passwordConfirmation;
    public RolesDTO rolesDTO;

    public SignupDTO() {
        this.rolesDTO = new RolesDTO();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPlainTextPassword() {
        return plainTextPassword;
    }

    public void setPlainTextPassword(String plainTextPassword) {
        this.plainTextPassword = plainTextPassword;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public RolesDTO getRolesDTO() {
        return rolesDTO;
    }

    public void setRolesDTO(RolesDTO roles) {
        this.rolesDTO = roles;
    }

    public boolean isPasswordMatching() {
        return this.passwordConfirmation.equals(this.plainTextPassword);
    }
}
