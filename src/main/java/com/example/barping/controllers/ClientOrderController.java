package com.example.barping.controllers;

import com.example.barping.entities.*;
import com.example.barping.repositories.UserRepository;
import com.example.barping.services.ClientOrderService;
import com.example.barping.services.BarItemService;
import com.example.barping.services.InventoryService;
import com.example.barping.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/orders")
public class ClientOrderController {

    @Autowired
    private ClientOrderService clientOrderService;

    @Autowired
    private BarItemService barItemService;

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/create")
    public String createOrderForm(Model model) {
        model.addAttribute("barItems", barItemService.findAll());
        return "orders/create";
    }


    @GetMapping("/list")
    public String listOrdersUndelivered(Model model) {
//        model.addAttribute("orders", clientOrderService.findDeliverablesOrders());
//        model.addAttribute("orders", clientOrderService.findIncompleteOrders());
          model.addAttribute("orders",clientOrderService.findActiveOrdersByUserId());
        return "orders/list-client";

    }
    @GetMapping("/all-orders")
    public String listAllOrdersReversed(Model model) {
//        model.addAttribute("orders", clientOrderService.findDeliverablesOrders());
//        model.addAttribute("orders", clientOrderService.findIncompleteOrders());
        List<ClientOrder > orders=clientOrderService.findAll();
        Collections.reverse(orders);
        model.addAttribute("orders",orders );
        return "orders/list-client-all";

    }



    @PostMapping("/save")
    public String saveOrder(
            @RequestParam String clientName,
            @RequestParam String clientContact,
            @RequestParam(value = "itemIds[]", required = false) Long[] itemIds,
            @RequestParam(value = "quantities[]", required = false) Integer[] quantities,
            Model model) {

        // Validate inputs
        if (clientName == null || clientName.isEmpty() || clientContact == null || clientContact.isEmpty()) {
            model.addAttribute("error", "Client name and contact are required.");
            model.addAttribute("barItems", barItemService.findAll());
            return "orders/create";
        }

        if (itemIds == null || itemIds.length == 0 || quantities == null || quantities.length != itemIds.length) {
            model.addAttribute("error", "At least one item with valid quantity is required.");
            model.addAttribute("barItems", barItemService.findAll());
            return "orders/create";
        }


        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user= userRepository.findByUsername(username);


        ClientOrder clientOrder = new ClientOrder();

        clientOrder.setUserId(user.getId());
        clientOrder.setClientName(clientName);
        clientOrder.setClientContact(clientContact);
        clientOrder.setOrderDate(LocalDate.now());

        Set<ClientOrderDetail> orderDetails = new HashSet<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (int i = 0; i < itemIds.length; i++) {
            Long itemId = itemIds[i];
            int quantity = quantities[i];

            if (quantity <= 0) continue;

            BarItem barItem = barItemService.findById(itemId);
            if (barItem == null) {
                model.addAttribute("error", "Invalid item selected.");
                model.addAttribute("barItems", barItemService.findAll());
                return "orders/create";
            }

            Inventory inventory = inventoryService.findByBarItemId(barItem.getId());
            if (inventory == null || inventory.getQuantity() < quantity) {
                model.addAttribute("error", "Insufficient stock for item: " + barItem.getName());
                model.addAttribute("barItems", barItemService.findAll());
                return "orders/create";
            }

            inventory.setQuantity(inventory.getQuantity() - quantity);
            inventoryService.save(inventory);

            ClientOrderDetail detail = new ClientOrderDetail();
            detail.setBarItem(barItem);
            detail.setQuantity(quantity);
            detail.setUnitPrice(BigDecimal.valueOf(barItem.getUnitPrice()));
            detail.setLineTotal(detail.getUnitPrice().multiply(BigDecimal.valueOf(quantity)));
            detail.setClientOrder(clientOrder);

            orderDetails.add(detail);
            totalAmount = totalAmount.add(detail.getLineTotal());
        }

        if (orderDetails.isEmpty()) {
            model.addAttribute("error", "No valid items selected for the order.");
            model.addAttribute("barItems", barItemService.findAll());
            return "orders/create";
        }

        clientOrder.setOrderDetails(orderDetails);
        clientOrder.setTotalAmount(totalAmount);

        // Save the new order
        clientOrderService.save(clientOrder);


        return "redirect:/orders/list"; // Redirect to the orders list page
    }



}
