package org.danlee.cardealer.controllers;

import org.danlee.cardealer.annotations.BuyersOnly;
import org.danlee.cardealer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TestController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/test")
    @BuyersOnly
    public String test() {
        return "home.html";
    }

    @GetMapping("/test/setSession")
    public String setSession(HttpSession httpSession) {
        httpSession.setAttribute("userId", userRepository.findAllBuyers().get(0).getId());
        return "home.html";
    }
}
