package com.ecs.ecs_reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer orderId;
    private Integer customerId;
    private Integer addressId;
    private List<Integer> productIds;
    private List<Integer> productQuantities;
    private String paymentType;
    private String paymentStatus;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String shippingStatus;
}
