package com.example.barping.controllers;

import com.example.barping.entities.BarItem;
import com.example.barping.entities.ClientOrder;
import com.example.barping.entities.ClientOrderDetail;
import com.example.barping.entities.Inventory;
import com.example.barping.services.ClientOrderService;
import com.example.barping.services.BarItemService;
import com.example.barping.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
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

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", clientOrderService.findAll());
        return "orders/list";
    }

    @GetMapping("/create")
    public String createOrderForm(Model model) {
        model.addAttribute("barItems", barItemService.findAll());
        return "orders/create";
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

        ClientOrder clientOrder = new ClientOrder();
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
                System.out.println("A puscat aici");
                model.addAttribute("error", "Invalid item selected.");
                model.addAttribute("barItems", barItemService.findAll());
                return "orders/create";
            }

            Inventory inventory = inventoryService.findByBarItemId(barItem.getId());

            System.out.println(inventory);
            if (inventory == null || inventory.getQuantity() < quantity) {
                System.out.println("A puscat aicisiaaicicicsaiisaidcsahjsafsaj");
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

        clientOrderService.save(clientOrder);

        return "redirect:/orders";
    }


}
