package com.example.customerorderapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    
    @Id
    @Column(name = "order_id")
    private Long id;
    
    @NotBlank(message = "Status is required")
    @Column(name = "status", length = 50)
    private String status;
    
    @Column(name = "gender", length = 1)
    private String gender;
    
    @Column(name = "created_at")
    private Long createdAt;
    
    @Column(name = "returned_at")
    private Long returnedAt;
    
    @Column(name = "shipped_at")
    private Long shippedAt;
    
    @Column(name = "delivered_at")
    private Long deliveredAt;
    
    @Min(value = 1, message = "Number of items must be at least 1")
    @Column(name = "num_of_item")
    private Integer numOfItem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}