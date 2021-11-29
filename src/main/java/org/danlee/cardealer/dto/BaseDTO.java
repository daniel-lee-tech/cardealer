package org.danlee.cardealer.dto;

public class BaseDTO {
    private boolean errorPresent;
    private String errorMessage;

    public boolean isErrorPresent() {
        return errorPresent;
    }

    public void setErrorPresent(boolean errorPresent) {
        this.errorPresent = errorPresent;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
