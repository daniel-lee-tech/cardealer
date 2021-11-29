package org.danlee.cardealer.controllers;

import org.danlee.cardealer.services.InventoryService;
import org.danlee.cardealer.viewmodels.CarWithTransaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class InventoryController {
    private final InventoryService inventoryService;
    
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping({"/", "/home", "/cars"})
    public String getCarsIndex(Model model) {
        ArrayList<CarWithTransaction> allCarsWithTransactions = inventoryService.combineCarsWithTransactions();
        model.addAttribute("allCars", allCarsWithTransactions);

        return "index.html";
    }

    @GetMapping("/inventory")
    public String getUnsoldCars(Model model) {
        model.addAttribute("unsoldCars", inventoryService.findUnsoldCars());

        return "inventory.html";
    }

    @GetMapping("/inventory/model/{modelName}")
    public String inventoryByModel(@PathVariable String modelName, Model model) {
        model.addAttribute("unsoldCars", inventoryService.findUnsoldCarsByModel(modelName));

        return "inventory.html";
    }

    @GetMapping("/inventory/search")
    public String searchPage(Model model) {
        model.addAttribute("models", inventoryService.getUnsoldModels());
        return "search.html";
    }
}
