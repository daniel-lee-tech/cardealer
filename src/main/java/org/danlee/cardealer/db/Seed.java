package org.danlee.cardealer.db;

import org.danlee.cardealer.entities.Car;
import org.danlee.cardealer.entities.Transaction;
import org.danlee.cardealer.entities.User;
import org.danlee.cardealer.enums.UserRoles;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Seed {
    private MockDatabase mockDatabase;

    public Seed(MockDatabase mockDatabase) {
        this.mockDatabase = mockDatabase;
    }

    public void seedData() {
        generateMockCars();
        generateMockTransactions();
        generateMockUsers();
    }

    private void generateMockCars() {
        mockDatabase.getAllCars().add(new Car("toyota", generateDateWithDayOffset(-10), 2340, "Camry", "xlm32", "really nice", 32133, "/images/democar1.jpg", true));
        mockDatabase.getAllCars().add(new Car("honda", generateDateWithDayOffset(-20), 2340, "accord", "dsa23", "really good", 1332, "/images/democar1.jpg", true));
        mockDatabase.getAllCars().add(new Car("lambo", generateDateWithDayOffset(-30), 2340, "v3", "dsf32", "really sexy", 43243, "/images/democar1.jpg", true));
        mockDatabase.getAllCars().add(new Car("airplane", generateDateWithDayOffset(-40), 2340, "g6", "fly232d", "really swag", 3213, "/images/democar2.jpg", false));
        mockDatabase.getAllCars().add(new Car("toyota", generateDateWithDayOffset(-50), 2340, "Camry", "d34d", "really expensive", 545645, "/images/democar2.jpg", false));
        mockDatabase.getAllCars().add(new Car("toyota", generateDateWithDayOffset(-70), 2340, "Camry", "d34d", "really luxury", 5435, "/images/democar2.jpg", false));
        mockDatabase.getAllCars().add(new Car("toyota", generateDateWithDayOffset(-90), 2340, "Camry", "d34d", "really bland", 54367, "/images/democar2.jpg", false));
        mockDatabase.getAllCars().add(new Car("toyota", generateDateWithDayOffset(-110), 2340, "Camry", "321fsd", "really kewl", 3242, "/images/democar2.jpg", true));
        mockDatabase.getAllCars().add(new Car("toyota", generateDateWithDayOffset(-210), 2340, "Camry", "321fsd", "really fire", 47562, "/images/democar2.jpg", true));
        mockDatabase.getAllCars().add(new Car("toyota", generateDateWithDayOffset(-310), 2340, "Camry", "321fsd", "really lit", 523, "/images/democar2.jpg", true));
    }


    private void generateMockTransactions() {
        var roles =  new ArrayList<UserRoles>();
        roles.add(UserRoles.Buyer);
        User buyer = new User("Daniel Lee", "dan@email.com", "I live somewhere", roles, "password");
        mockDatabase.addUser(buyer);
        mockDatabase.getAllTransactions().add(new Transaction(buyer, mockDatabase.getAllCars().get(1), generateDateWithDayOffset(-20), "CREDIT CARD: 11111111"));
        mockDatabase.getAllTransactions().add(new Transaction(buyer, mockDatabase.getAllCars().get(0), new Date(), "CREDIT CARD: 3213214213213"));
    }

    private Date generateDateWithDayOffset(int numDays) {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, numDays);

        return c.getTime();
    }

    private void generateMockUsers() {
        var roles =  new ArrayList<UserRoles>();
        roles.add(UserRoles.Seller);
        User seller = new User("Seller Lee", "seller@email.com", "I live somewhere", roles , "password");
        mockDatabase.addUser(seller);
    }

}
