package org.danlee.cardealer.controllers;

import org.danlee.cardealer.dto.CarDTO;
import org.danlee.cardealer.entities.Car;
import org.danlee.cardealer.repositories.CarRepository;

import org.danlee.cardealer.services.ImageUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class CarController {
    private final CarRepository carRepository;
    private final ImageUploadService imageUploadService;

    public CarController(CarRepository carRepository, ImageUploadService imageUploadService) {
        this.carRepository = carRepository;
        this.imageUploadService = imageUploadService;
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
        model.addAttribute("newCarDTO", new CarDTO());
        return "newCarForm.html";
    }

    @PostMapping("/cars/new")
    public String addNewCar(@ModelAttribute("newCarDTO") CarDTO carDTO, Model model) {
        String imageName = imageUploadService.validateImageNameUniqueness(carDTO.getImageFile());

        try {
            imageUploadService.handleImageUpload(imageName, carDTO.getImageFile());
            carDTO.setImageUrl("/images/" + imageName);

            carRepository.save(carDTO);

            return "redirect:/inventory";

        } catch (Exception e) {
            System.out.println("Image could not be saved");
            model.addAttribute("newCarDTO", new CarDTO());
            return "newCarForm.html";
        }
    }
}
