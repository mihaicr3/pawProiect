package com.example.barping.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/client")
public class ClientController {
    @GetMapping("/dashboard")
    public String clientDashboard() {
        return "dashboards/client-dashboard";
    }
}