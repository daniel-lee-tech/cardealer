package org.danlee.cardealer.controllers;

import org.danlee.cardealer.repositories.InventoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Inventory {
    private final InventoryRepository inventoryRepository;

    public Inventory(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

}
