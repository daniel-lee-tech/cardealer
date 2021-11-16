package org.danlee.cardealer.services;

import org.danlee.cardealer.entities.Car;
import org.danlee.cardealer.entities.Transaction;
import org.danlee.cardealer.repositories.CarRepository;
import org.danlee.cardealer.repositories.TransactionRepository;
import org.danlee.cardealer.viewmodels.CarWithTransaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class InventoryService {
    private final CarRepository carRepository;
    private final TransactionRepository transactionRepository;

    public InventoryService(CarRepository carRepository, TransactionRepository transactionRepository) {
        this.carRepository = carRepository;
        this.transactionRepository = transactionRepository;
    }

    public boolean isUnsold(Car car) {
        Car carInDatabase = carRepository.findById(car.getId());

        if (carInDatabase == null) {
            new Exception("Car is not in the database");
        }

        return transactionRepository.getTransactionByCar(car) == null;
    }

    public ArrayList<String> getUnsoldModels() {
        ArrayList<Car> allCars = carRepository.findAllCars();
        ArrayList<String> unsoldModels = new ArrayList<>();

        for (Car loopedCar: allCars) {
            if (isUnsold(loopedCar)) {
                unsoldModels.add(loopedCar.getModel());
            }
        }

        return removeDuplicates(unsoldModels);
    }

    public ArrayList<String> removeDuplicates(ArrayList<String> items) {
        HashMap<String, Integer> objectCount = new HashMap<>();

        for (String loopedItem: items) {
            objectCount.putIfAbsent(loopedItem, 0);
            Integer currentCount =  objectCount.get(loopedItem);
            objectCount.put(loopedItem, currentCount + 1);
        }

        return new ArrayList<String>(objectCount.keySet().stream().toList());
    }

    public ArrayList<Car> findUnsoldCars() {
        ArrayList<Car> allCars = carRepository.findAllCars();
        return new ArrayList<Car>(allCars.stream().filter(this::isUnsold).toList());
    }

    public ArrayList<Car> findUnsoldCarsByModel(String modelName) {
        ArrayList<Car> unsoldCars = findUnsoldCars();
        return new ArrayList<Car>(unsoldCars.stream().filter(car -> car.getModel().equals(modelName)).toList());
    }

    public ArrayList<CarWithTransaction> combineCarsWithTransactions() {
        ArrayList<Car> allCars = carRepository.findAllCars();

        ArrayList<CarWithTransaction> allCarsWithTransactions = new ArrayList<>();

        for (Car loopedCar: allCars) {
            CarWithTransaction carWithTransaction = new CarWithTransaction();
            carWithTransaction.setCar(loopedCar);
            carWithTransaction.setTransaction(transactionRepository.getTransactionByCar(loopedCar));
            allCarsWithTransactions.add(carWithTransaction);
        }
        return allCarsWithTransactions;
    }
}
