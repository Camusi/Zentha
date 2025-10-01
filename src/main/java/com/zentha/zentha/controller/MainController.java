package com.zentha.zentha.controller;


import com.zentha.zentha.entity.User;
import com.zentha.zentha.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class MainController {

    private final MainService mainservice;

    @Autowired
    MainController(MainService mainService) {
        this.mainservice = mainService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", mainservice.getMessage());
        return "index";
    }

    @PostMapping("/update/score/{uid}")
    public void updateScore(@PathVariable Long uid, Integer score) {
        mainservice.getUser(uid).ifPresent(user -> mainservice.setScore(user, score));
    }

    @ResponseBody
    @GetMapping("/get/score/{uid}")
    public Integer getScore(@PathVariable Long uid) {
        Optional<User> user = mainservice.getUser(uid);
        return user.isPresent() ? user.get().getScore() : 0;
    }

}
