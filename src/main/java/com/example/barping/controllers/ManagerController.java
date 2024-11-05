package com.example.barping.controllers;


import com.example.barping.entities.BarItem;
import com.example.barping.services.BarItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @GetMapping("/dashboard")
    public String managerDashboard() {
        return "Manager Dashboard";
    }

    @Autowired
    private BarItemService barItemService;

    // Display the add item form
    @GetMapping("/add-item")
    public String showAddItemForm(Model model) {
        model.addAttribute("barItem", new BarItem()); // Add a new BarItem object to the model
        return "/add-item";
    }

    // Handle form submission
    @PostMapping("/add-item")
    public String addItem(@ModelAttribute BarItem barItem) {
        barItemService.save(barItem); // Save the BarItem to the database
        return "redirect:/manager/add-item?success"; // Redirect with a success flag
    }
}
