package org.danlee.cardealer.db;

import org.danlee.cardealer.entities.User;
import org.danlee.cardealer.entities.Car;
import org.danlee.cardealer.entities.Transaction;
import org.danlee.cardealer.enums.UserRoles;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Component
public class MockDatabase {
    private ArrayList<Car> allCars = new ArrayList<Car>();
    private ArrayList<Transaction> allTransactions = new ArrayList<Transaction>();
    private ArrayList<User> allUsers = new ArrayList<User>();

    public MockDatabase() {
        generateDataFromSeed();
    }

    public ArrayList<Car> getAllCars() {
        return allCars;
    }

    public void setAllCars(ArrayList<Car> allCars) {
        this.allCars = allCars;
    }

    public ArrayList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public void setAllTransactions(ArrayList<Transaction> allTransactions) {
        this.allTransactions = allTransactions;
    }

    public void addUser(User user) {
        allUsers.add(user);
    }

    public void addTransaction(Transaction transaction) {
        allTransactions.add(transaction);
    }

    public void addCar(Car car) {
        allCars.add(car);
    }

    public ArrayList<User> getAllUsers() {
        return this.allUsers;
    }

    public void generateDataFromSeed() {
        new Seed(this).seedData();
    }
}
