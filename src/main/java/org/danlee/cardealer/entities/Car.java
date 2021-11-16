package org.danlee.cardealer.entities;

import java.util.Date;
import java.util.UUID;

public class Car {
    private UUID id;
    private String manufacturer;
    private Date dateOfPurchase;
    private int mileage;
    private String make;
    private String model;
    private String description;
    private double price;
    private String imageUrl;
    private boolean used;

    public Car() {
        this.id = UUID.randomUUID();
    }

    public Car(String manufacturer, Date dateOfPurchase, int mileage, String make, String model, String description, double price, String imageUrl, boolean used) {
        this.id = UUID.randomUUID();
        this.manufacturer = manufacturer;
        this.dateOfPurchase = dateOfPurchase;
        this.mileage = mileage;
        this.make = make;
        this.model = model;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.used = used;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        Date dateOfListing = getDateOfPurchase();
        Date currentDate = new Date();
        long millisecondsInADay = 86_400_000L;
        long millisecondsIn120Days = millisecondsInADay * 120;
        if (currentDate.getTime() - dateOfListing.getTime() >= millisecondsIn120Days) {
            return price * .9;
        }
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
