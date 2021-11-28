package org.danlee.cardealer.dto;

public class RolesDTO {
    private boolean buyerRole;
    private boolean sellerRole;
    private boolean adminRole;

    public boolean isBuyerRole() {
        return buyerRole;
    }

    public void setBuyerRole(boolean buyerRole) {
        this.buyerRole = buyerRole;
    }

    public boolean isSellerRole() {
        return sellerRole;
    }

    public void setSellerRole(boolean sellerRole) {
        this.sellerRole = sellerRole;
    }

    public boolean isAdminRole() {
        return adminRole;
    }

    public void setAdminRole(boolean adminRole) {
        this.adminRole = adminRole;
    }
}
