package org.danlee.cardealer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public ModelAndView homePage(Model model) {
        return new ModelAndView("home");
    }
}
