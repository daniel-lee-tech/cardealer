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
        generateMockCars();
        generateMockTransactions();
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

    public void generateMockCars() {
        allCars.add(new Car("toyota", generateDateWithDayOffset(-10), 2340, "Camry", "xlm32", "really nice", 32133, "/images/democar1.jpg", true));
        allCars.add(new Car("honda", generateDateWithDayOffset(-20), 2340, "accord", "dsa23", "really good", 1332, "/images/democar1.jpg", true));
        allCars.add(new Car("lambo", generateDateWithDayOffset(-30), 2340, "v3", "dsf32", "really sexy", 43243, "/images/democar1.jpg", true));
        allCars.add(new Car("airplane", generateDateWithDayOffset(-40), 2340, "g6", "fly232d", "really swag", 3213, "/images/democar2.jpg", false));
        allCars.add(new Car("toyota", generateDateWithDayOffset(-50), 2340, "Camry", "d34d", "really expensive", 545645, "/images/democar2.jpg", false));
        allCars.add(new Car("toyota", generateDateWithDayOffset(-70), 2340, "Camry", "d34d", "really luxury", 5435, "/images/democar2.jpg", false));
        allCars.add(new Car("toyota", generateDateWithDayOffset(-90), 2340, "Camry", "d34d", "really bland", 54367, "/images/democar2.jpg", false));
        allCars.add(new Car("toyota", generateDateWithDayOffset(-110), 2340, "Camry", "321fsd", "really kewl", 3242, "/images/democar2.jpg", true));
        allCars.add(new Car("toyota", generateDateWithDayOffset(-210), 2340, "Camry", "321fsd", "really fire", 47562, "/images/democar2.jpg", true));
        allCars.add(new Car("toyota", generateDateWithDayOffset(-310), 2340, "Camry", "321fsd", "really lit", 523, "/images/democar2.jpg", true));
    }

    public void generateMockTransactions() {
        User buyer = new User("Daniel Lee", "dan@email.com", "I live somewhere", UserRoles.Buyer);
        addUser(buyer);
        allTransactions.add(new Transaction(buyer, allCars.get(1), generateDateWithDayOffset(-20), "CREDIT CARD: 11111111"));
        allTransactions.add(new Transaction(buyer, allCars.get(0), new Date(), "CREDIT CARD: 3213214213213"));
    }

    private void addUser(User user) {
        allUsers.add(user);
    }

    private Date generateDateWithDayOffset(int numDays) {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, numDays);

        return c.getTime();
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
}
