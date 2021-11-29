package org.danlee.cardealer.controllers;

import org.danlee.cardealer.dto.LoginDTO;
import org.danlee.cardealer.dto.SignupDTO;
import org.danlee.cardealer.entities.User;
import org.danlee.cardealer.repositories.UserRepository;
import org.danlee.cardealer.utils.PasswordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"/signup"})
    public ModelAndView signup() {
        return new ModelAndView("signup.html", "newUser", new SignupDTO());
    }


    @PostMapping({"/signup"})
    public ModelAndView postSignup(@ModelAttribute("newUser") SignupDTO signupDTO, Model model, HttpSession session) {
        User foundUser = userRepository.findByEmail(signupDTO.getUser().getEmail());

        if (foundUser != null) {
            signupDTO.setErrorPresent(true);
            signupDTO.setErrorMessage("Email is already in use");
            model.addAttribute("errorMessage", "Email is already in use.");
            return new ModelAndView("signup.html", "newUser", signupDTO);
        }

        if (!signupDTO.isPasswordMatching()) {
            signupDTO.setErrorPresent(true);
            signupDTO.setErrorMessage("Passwords do not match");
            return new ModelAndView("signup.html", "newUser", signupDTO);
        }

        try {
            var savedUser = userRepository.save(signupDTO);
            session.setAttribute("userId", savedUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:/home");
    }

    @GetMapping({"/login"})
    public ModelAndView login() {
        return new ModelAndView("login.html", "newLogin", new LoginDTO());
    }

    @PostMapping({"/login"})
    public ModelAndView postLogin(@ModelAttribute("newLogin") LoginDTO loginDTO, HttpSession httpSession) {
        String email = loginDTO.getEmail();
        String passwordDigest = PasswordUtils.createPasswordDigest(loginDTO.getPlainTextPassword());

        User possibleUser = userRepository.findByEmailAndPasswordDigest(email, passwordDigest);

        if (possibleUser == null) {

            loginDTO.setErrorPresent(true);
            loginDTO.setErrorMessage("Wrong Credentials");
            return new ModelAndView("login.html", "newLogin", loginDTO);
        }

        httpSession.setAttribute("userId", possibleUser.getId());
        return new ModelAndView("redirect:/home");
    }

    @GetMapping({"/logout"})
    public ModelAndView logout(HttpSession httpSession) {
        httpSession.removeAttribute("userId");
        return new ModelAndView("redirect:/home");
    }
}
