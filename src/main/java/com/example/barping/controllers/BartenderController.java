package com.example.barping.controllers;

import com.example.barping.entities.ClientOrder;
import com.example.barping.services.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;



@Controller
@RequestMapping("/bartender")
public class BartenderController {



    @GetMapping("/dashboard")
    public String bartenderDashboard() {
        return "dashboards/bartender-dashboard";
    }


    @Autowired
    private ClientOrderService clientOrderService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;




    @GetMapping("/done")
    public String listOrdersUndelivered(Model model) {
        model.addAttribute("orders", clientOrderService.findDeliverablesOrders());
        return "orders/list-deliverable";

    }
    @GetMapping("/to-do")
    public String listOrders(Model model) {
        model.addAttribute("orders", clientOrderService.findIncompleteOrders());
        return "orders/list";
    }

    @PostMapping("/deliver/{id}")
    public String markOrderAsDelivered(@PathVariable Long id) {
        // Update order state to "delivered"
        ClientOrder order = clientOrderService.findById(id);
        if (order == null || order.getOrderCompletion() != 1) {
            // Handle invalid order or incorrect status
            return "redirect:/orders?error=Invalid+order+state";
        }

        clientOrderService.updateOrderCompletion(id,2);


        return "redirect:/bartender/done";
    }

    @PostMapping("/complete/{id}")
    public String markOrderAsComplete(@PathVariable Long id) {
        // Update order completion status
        clientOrderService.updateOrderCompletion(id, 1);

        // Notify all connected clients about the updated order
        messagingTemplate.convertAndSend("/topic/orders", id);

        return "redirect:/bartender/to-do"; // Adjust path to your orders page
    }
}