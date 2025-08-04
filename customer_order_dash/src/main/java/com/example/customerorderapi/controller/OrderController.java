package com.example.customerorderapi.controller;

import com.example.customerorderapi.entity.Order;
import com.example.customerorderapi.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/with-user")
    public ResponseEntity<List<Order>> getAllOrdersWithUser() {
        List<Order> orders = orderService.getAllOrdersWithUser();
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/{id}/with-user")
    public ResponseEntity<Order> getOrderByIdWithUser(@PathVariable Long id) {
        Order order = orderService.getOrderByIdWithUser(id);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/customer/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable String status) {
        List<Order> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/date-range")
    public ResponseEntity<List<Order>> getOrdersByDateRange(
            @RequestParam Long startTimestamp,
            @RequestParam Long endTimestamp) {
        List<Order> orders = orderService.getOrdersByDateRange(startTimestamp, endTimestamp);
        return ResponseEntity.ok(orders);
    }
    
    @PostMapping("/customer/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long userId, @Valid @RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @Valid @RequestBody Order orderDetails) {
        Order updatedOrder = orderService.updateOrder(id, orderDetails);
        return ResponseEntity.ok(updatedOrder);
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        Order updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/customer/{userId}/count")
    public ResponseEntity<Long> getOrderCountByUserId(@PathVariable Long userId) {
        Long count = orderService.getOrderCountByUserId(userId);
        return ResponseEntity.ok(count);
    }
}