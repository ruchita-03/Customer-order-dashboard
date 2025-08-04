package com.example.customerorderapi.service;

import com.example.customerorderapi.entity.Order;
import com.example.customerorderapi.entity.User;
import com.example.customerorderapi.repository.OrderRepository;
import com.example.customerorderapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserService userService;
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public List<Order> getAllOrdersWithUser() {
        return orderRepository.findAllWithUser();
    }
    
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }
    
    public Order getOrderByIdWithUser(Long id) {
        return orderRepository.findByIdWithUser(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }
    
    public List<Order> getOrdersByUserId(Long userId) {
        // Verify user exists
        userService.getUserById(userId);
        return orderRepository.findByUserId(userId);
    }
    
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
    
    public List<Order> getOrdersByDateRange(Long startTimestamp, Long endTimestamp) {
        return orderRepository.findByCreatedAtBetween(startTimestamp, endTimestamp);
    }
    
    public Order createOrder(Order order, Long userId) {
        User user = userService.getUserById(userId);
        order.setUser(user);
        order.setCreatedAt(System.currentTimeMillis());
        return orderRepository.save(order);
    }
    
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = getOrderById(id);
        
        order.setStatus(orderDetails.getStatus());
        order.setGender(orderDetails.getGender());
        order.setNumOfItem(orderDetails.getNumOfItem());
        order.setReturnedAt(orderDetails.getReturnedAt());
        order.setShippedAt(orderDetails.getShippedAt());
        order.setDeliveredAt(orderDetails.getDeliveredAt());
        
        return orderRepository.save(order);
    }
    
    public Order updateOrderStatus(Long id, String status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        
        // Update relevant timestamp based on status
        Long now = System.currentTimeMillis();
        switch (status.toUpperCase()) {
            case "SHIPPED":
                order.setShippedAt(now);
                break;
            case "DELIVERED":
                order.setDeliveredAt(now);
                break;
            case "RETURNED":
                order.setReturnedAt(now);
                break;
        }
        
        return orderRepository.save(order);
    }
    
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }
    
    public Long getOrderCountByUserId(Long userId) {
        return orderRepository.countByUserId(userId);
    }
}