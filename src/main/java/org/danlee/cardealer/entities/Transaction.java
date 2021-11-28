package org.danlee.cardealer.entities;

import java.util.Date;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private User buyer;
    private Car car;
    private Date transactionDate;
    private String paymentInformation;

    public Transaction() {
        this.id = UUID.randomUUID();
    }

    public Transaction(User buyer, Car car, Date transactionDate, String paymentInformation) {
        this.id = UUID.randomUUID();
        this.buyer = buyer;
        this.car = car;
        this.transactionDate = transactionDate;
        this.paymentInformation = paymentInformation;
    }

    public Transaction(Car car, User buyer) {
        this.id = UUID.randomUUID();
        this.car = car;
        this.buyer = buyer;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public long getTransactionTime() {
        return getTransactionDate().getTime();
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(String paymentInformation) {
        this.paymentInformation = paymentInformation;
    }
}
