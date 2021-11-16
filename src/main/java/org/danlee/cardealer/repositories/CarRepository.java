package org.danlee.cardealer.repositories;

import org.danlee.cardealer.db.MockDatabase;
import org.danlee.cardealer.entities.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public class CarRepository {
    private final MockDatabase mockDatabase;

    CarRepository(MockDatabase mockDatabase) {
        this.mockDatabase = mockDatabase;
    }

    public ArrayList<Car> findAllCars() {
        return mockDatabase.getAllCars();
    }

    public Car findById(UUID id) {
        ArrayList<Car> allCars = mockDatabase.getAllCars();

        for (Car loopedCar: allCars) {
            if (loopedCar.getId().equals(id)) {
                return loopedCar;
            }
        }

        return null;
    }

    public ArrayList<String> getAllModels() {
        ArrayList<Car> allCars = mockDatabase.getAllCars();
        ArrayList<String> allModels = new ArrayList<>();

        for (Car loopedCar: allCars) {
            if (!allModels.contains(loopedCar.getModel())) {
                allModels.add(loopedCar.getModel());
            }
        }

        return allModels;
    }
}
