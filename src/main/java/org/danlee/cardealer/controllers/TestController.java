package org.danlee.cardealer.controllers;

import org.danlee.cardealer.annotations.BuyersOnly;
import org.danlee.cardealer.db.MockDatabase;
import org.danlee.cardealer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Controller
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockDatabase mockDatabase;


    @GetMapping("/test")
    @BuyersOnly
    public String test() {
        System.out.println(userRepository.findAll().size());

        return "home.html";
    }

    @GetMapping("/test/setSession")
    public String setSession(HttpSession httpSession) {
        httpSession.setAttribute("userId", userRepository.findAllBuyers().get(0).getId());
        return "home.html";
    }

    @GetMapping("/data")
    @ResponseBody
    public<T> HashMap<String, T> showData() {
        HashMap<String, T> data = new HashMap<String, T>();
        data.put("users", (T) mockDatabase.getAllUsers());
        data.put("cars", (T) mockDatabase.getAllCars());
        data.put("transactions", (T) mockDatabase.getAllTransactions());
        return data;
    }
}
