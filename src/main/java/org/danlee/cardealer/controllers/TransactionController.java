package org.danlee.cardealer.controllers;

import org.danlee.cardealer.annotations.BuyersOnly;
import org.danlee.cardealer.entities.User;
import org.danlee.cardealer.entities.Car;
import org.danlee.cardealer.entities.Transaction;
import org.danlee.cardealer.repositories.CarRepository;
import org.danlee.cardealer.repositories.TransactionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Controller
public class TransactionController {
    private final TransactionRepository transactionRepository;
    private final CarRepository carRepository;

    TransactionController(TransactionRepository transactionRepository, CarRepository carRepository) {
        this.transactionRepository = transactionRepository;
        this.carRepository = carRepository;
    }

    @GetMapping("/transactions")
    public String getTransactions(Model model) {
        model.addAttribute("allTransactions",transactionRepository.getAllTransaction());
        return "transactions.html";
    }

    @GetMapping("/transactions/{id}")
    public String getTransactions(@PathVariable UUID id,  Model model) {
        Transaction possibleTransaction = transactionRepository.findById(id);

        if (possibleTransaction == null) {
            model.addAttribute("foundTransaction", false);
        } else {
            model.addAttribute("foundTransaction", true);
            model.addAttribute("transaction", possibleTransaction);
        }

        return "transactionDetails.html";
    }

    @GetMapping("/buyers/{id}")
    public String showBuyer(@PathVariable UUID id, Model model) {
        User possibleBuyer = transactionRepository.findBuyerById(id);

        if (possibleBuyer == null) {
            model.addAttribute("foundBuyer", false);
        } else {
            model.addAttribute("foundBuyer", true);
            model.addAttribute("buyer", possibleBuyer);
        }

        return "buyerDetails.html";
    }

    @GetMapping("/transactions/new/{carId}")
    public String buyCarForm(@PathVariable UUID carId, Model model) {
        Car possibleCar = carRepository.findById(carId);
        if (possibleCar == null) {
            model.addAttribute("foundCar", false);
        } else {
            model.addAttribute("foundCar", true);
        }

        Transaction newTransaction = new Transaction(possibleCar, new User());
        model.addAttribute("newTransaction", newTransaction);

        return "buyCarForm.html";
    }

    @PostMapping("/transactions/new/{carId}")
    @BuyersOnly
    public String buyCarTransaction(@PathVariable UUID carId, @ModelAttribute Transaction transaction, Model model) {
        Car possibleCar = carRepository.findById(carId);
        if (possibleCar == null) {
            model.addAttribute("validCarId", false);
            return "inventory.html";
        }

        transaction.setCar(possibleCar);
        transaction.setTransactionDate(new Date());
        transactionRepository.save(transaction);

        return "redirect:/transactions/"+transaction.getId();
    }
}
