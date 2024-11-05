package com.example.barping.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/bartender")
public class BartenderController {
    @GetMapping("/dashboard")
    public String bartenderDashboard() {
        return "dashboards/bartender-dashboard";
    }
}