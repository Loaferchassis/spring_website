package com.alexander.controllers;

import com.alexander.SecurityConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private static Logger log = LogManager.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        log.info("Get request /login");
        if (error != null) {
            log.info("Wrong username or password.");
            model.addAttribute("error", "Wrong username or password");
        }
        if (logout != null) {
            log.info("User is logged out.");
            model.addAttribute("message", "Logged out");
        }
        return "login";
    }

}
