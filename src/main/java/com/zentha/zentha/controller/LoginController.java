package com.zentha.zentha.controller;

import com.zentha.zentha.entity.User;
import com.zentha.zentha.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final MainService mainService;

    @Autowired
    public LoginController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // Thymeleaf template
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam Integer score, Model model) {
        // In this simple version, every login creates a new user with the score
        User user = new User(score);
        mainService.saveUser(user); // we’ll add this in the service
        model.addAttribute("user", user);
        return "redirect:/"; // after login, go to dashboard page
    }
}
