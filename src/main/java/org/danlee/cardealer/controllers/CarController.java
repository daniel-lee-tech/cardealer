package org.danlee.cardealer.controllers;

import org.danlee.cardealer.entities.Car;
import org.danlee.cardealer.repositories.CarRepository;
import org.danlee.cardealer.repositories.InventoryRepository;
import org.danlee.cardealer.repositories.TransactionRepository;
import org.danlee.cardealer.viewmodels.CarWithTransaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.UUID;

@Controller
public class CarController {
    private final CarRepository carRepository;
    private final InventoryRepository inventoryRepository;
    private final TransactionRepository transactionRepository;

    public CarController(CarRepository carRepository, InventoryRepository inventoryRepository, TransactionRepository transactionRepository) {
        this.carRepository = carRepository;
        this.inventoryRepository = inventoryRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping({"/cars", "/"})
    public String getCarsIndex(Model model) {
        ArrayList<Car> allCars = carRepository.findAllCars();

        ArrayList<CarWithTransaction> allCarsWithTransactions = new ArrayList<>();

        for (Car loopedCar: allCars) {
            CarWithTransaction carWithTransaction = new CarWithTransaction();
            carWithTransaction.setCar(loopedCar);
            carWithTransaction.setTransaction(transactionRepository.getTransactionByCar(loopedCar));
            allCarsWithTransactions.add(carWithTransaction);
        }

        model.addAttribute("allCars", allCarsWithTransactions);

        return "index.html";
    }



    @GetMapping("/cars/{id}")
    public String showCar(@PathVariable UUID id, Model model) {
        Car possibleCar = carRepository.findById(id);
        if (possibleCar == null) {
            model.addAttribute("foundCar", false);
        } else {
            model.addAttribute("foundCar", true);
            model.addAttribute("car", possibleCar);
        }

        return "carDetails.html";
    }

    @GetMapping("/carsbymodel/{modelName}")
    public String inventoryByModel(@PathVariable String modelName, Model model) {
        model.addAttribute("unsoldCars", inventoryRepository.findUnsoldCarsByModel(modelName));

        return "inventory.html";
    }

    @GetMapping("/cars/search")
    public String searchPage(Model model) {
        model.addAttribute("models", carRepository.getAllModels());
        return "search.html";
    }

    @GetMapping("/cars/inventory")
    public String getUnsoldCars(Model model) {
        model.addAttribute("unsoldCars", inventoryRepository.findUnsoldCars());

        return "inventory.html";
    }

    @GetMapping("/cars/inventory/new")
    public String newInventoryForm(Model model) {
        model.addAttribute("newCar", new Car());
        return "newCarForm.html";
    }
}
