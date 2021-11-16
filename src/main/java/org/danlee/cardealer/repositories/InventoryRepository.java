package org.danlee.cardealer.repositories;

import org.danlee.cardealer.db.MockDatabase;
import org.danlee.cardealer.entities.Buyer;
import org.danlee.cardealer.entities.Car;
import org.danlee.cardealer.entities.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public class InventoryRepository {
    private final MockDatabase mockDatabase;
    private final TransactionRepository transactionRepository;

    public InventoryRepository(MockDatabase mockDatabase, TransactionRepository transactionRepository) {
        this.mockDatabase = mockDatabase;
        this.transactionRepository = transactionRepository;
    }
    public ArrayList<Car> findUnsoldCars() {
        ArrayList<Car> allCars = mockDatabase.getAllCars();

        return new ArrayList<Car>(allCars.stream().filter(car -> !transactionRepository.checkTransactionForCar(car)).toList());
    }

    public ArrayList<Car> findUnsoldCarsByModel(String model) {
        ArrayList<Car> unsoldCars = findUnsoldCars();

        ArrayList<Car> filteredCars = new ArrayList<>();

        for (Car loopedCar: unsoldCars) {
            if (loopedCar.getModel().equals(model)) {
                filteredCars.add(loopedCar);
            }
        }

        return filteredCars;

    }
}
