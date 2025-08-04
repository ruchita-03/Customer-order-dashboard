package com.example.customerorderapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @Column(name = "id")
    private Long id;
    
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than 50 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must be less than 100 characters")
    @Column(name = "email", nullable = false)
    private String email;
    
    @Min(value = 1, message = "Age must be greater than 0")
    @Max(value = 150, message = "Age must be less than 150")
    @Column(name = "age")
    private Integer age;
    
    @Column(name = "gender", length = 1)
    private String gender;
    
    @Size(max = 50, message = "State must be less than 50 characters")
    @Column(name = "state")
    private String state;
    
    @Size(max = 255, message = "Street address must be less than 255 characters")
    @Column(name = "street_address")
    private String streetAddress;
    
    @Size(max = 20, message = "Postal code must be less than 20 characters")
    @Column(name = "postal_code")
    private String postalCode;
    
    @Size(max = 100, message = "City must be less than 100 characters")
    @Column(name = "city")
    private String city;
    
    @Size(max = 100, message = "Country must be less than 100 characters")
    @Column(name = "country")
    private String country;
    
    @Column(name = "latitude", precision = 9, scale = 6)
    private BigDecimal latitude;
    
    @Column(name = "longitude", precision = 9, scale = 6)
    private BigDecimal longitude;
    
    @Size(max = 50, message = "Traffic source must be less than 50 characters")
    @Column(name = "traffic_source")
    private String trafficSource;
    
    @Column(name = "created_at")
    private Long createdAt;  // Changed to Long to match your database
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Order> orders;
    
    // Helper method to get order count
    public int getOrderCount() {
        return orders != null ? orders.size() : 0;
    }
}