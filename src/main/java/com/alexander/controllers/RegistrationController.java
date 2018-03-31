package com.alexander.controllers;

import com.alexander.SecurityConfig;
import com.alexander.models.User;
import com.alexander.services.SecurityService;
import com.alexander.services.UserService;
import com.alexander.validators.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private static Logger log = LogManager.getLogger(RegistrationController.class);


    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        log.info("Get request /registration");
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        log.info("Post request /registration");

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("Error while register. Redirect to registration.");
            return "registration";
        }

        userService.save(user);

        securityService.autologin(user.getLogin(), user.getPassword());

        log.info("New Account created.");

        return "redirect:/home";
    }
}
