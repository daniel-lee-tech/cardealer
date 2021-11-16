package org.danlee.cardealer.controllers;

import org.danlee.cardealer.entities.Car;
import org.danlee.cardealer.repositories.CarRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class CarController {
    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
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


    @GetMapping("/cars/new")
    public String newInventoryForm(Model model) {
        model.addAttribute("newCar", new Car());
        return "newCarForm.html";
    }
}
