package com.ecs.ecs_reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderFinalDto {
    private int orderId;
    private CustomerDto customer;
    private AddressDto shippingAddress;
    private List<ProductFinalDto> products;
    private String paymentType;
    private String paymentStatus;
    private Date orderDate;
    private Date deliveryDate;
    private String shippingStatus;
}
