package org.danlee.cardealer.viewmodels;

import org.danlee.cardealer.entities.Car;
import org.danlee.cardealer.entities.Transaction;

public class CarWithTransaction {
    private Car car;
    private Transaction transaction;


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
