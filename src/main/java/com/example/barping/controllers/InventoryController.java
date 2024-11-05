package com.example.barping.controllers;

import com.example.barping.entities.BarItem;
import com.example.barping.entities.Inventory;
import com.example.barping.services.BarItemService;
import com.example.barping.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manager/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private BarItemService barItemService;

    @GetMapping
    public String getInventoryList(Model model) {
        List<Inventory> inventoryList = inventoryService.findAll();
        model.addAttribute("inventoryList", inventoryList);
        return "inventory-list"; // This will map to inventory-list.html
    }

    @PostMapping("/save")
    public String saveInventoryList(@ModelAttribute("inventoryList") List<Inventory> inventoryList) {
        for (Inventory inventory : inventoryList) {
            inventoryService.save(inventory);
        }
        return "redirect:/manager/inventory"; // Redirect back to the inventory list page
    }

    @GetMapping("/add")
    public String showAddInventoryForm(Model model) {
        List<BarItem> barItems = barItemService.findAll();
        model.addAttribute("barItems", barItems);
        model.addAttribute("newInventory", new Inventory()); // Create an empty Inventory object
        return "add-inventory"; // This will map to add-inventory.html
    }

    @PostMapping("/add")
    public String addInventoryItem(@ModelAttribute("newInventory") Inventory newInventory) {
        BarItem barItem = newInventory.getBarItem();

        // Check if there is already an inventory item for this BarItem
        Inventory existingInventory = inventoryService.findAll().stream()
                .filter(inventory -> inventory.getBarItem().getId().equals(barItem.getId()))
                .findFirst()
                .orElse(null);

        if (existingInventory != null) {
            // If the item exists, update the quantity by adding the new quantity
            existingInventory.setQuantity(existingInventory.getQuantity() + newInventory.getQuantity());
            inventoryService.save(existingInventory);
        } else {
            // If the item does not exist, add it as a new inventory item
            inventoryService.save(newInventory);
        }

        return "redirect:/manager/inventory"; // Redirect back to the inventory list page
    }

}
