package com.example.barping.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
    @GetMapping("/dashboard")
    public String clientDashboard() {
        return "Client Dashboard";
    }
}