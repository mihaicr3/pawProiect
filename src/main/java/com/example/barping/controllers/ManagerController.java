package com.example.barping.controllers;


import com.example.barping.entities.BarItem;
import com.example.barping.entities.ClientOrder;
import com.example.barping.services.BarItemService;
import com.example.barping.services.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @GetMapping("/dashboard")
    public String managerDashboard() {
        return "dashboards/manager-dashboard";
    }

    @Autowired
    private BarItemService barItemService;
    @Autowired
    private ClientOrderService clientOrderService;

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

    @GetMapping("/sold-main")
    public String showSoldForm() {
        return "sold/sold-selectare"; // Render the form for date selection
    }

    // Handle the GET request for filtering orders
    @GetMapping("/filter-orders")
    public String filterOrders(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {
        // Fetch orders between startDate and endDate
        List<ClientOrder> orders = clientOrderService.findByOrderDateBetween(startDate, endDate);
        model.addAttribute("orders", orders);
        return "sold/sold-comenzi"; // Render the orders display page
    }
}
