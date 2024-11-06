package com.ecs.ecs_reviews.feign;

import com.ecs.ecs_reviews.dto.CartDto;
import com.ecs.ecs_reviews.dto.OrderFinalDto;
import com.ecs.ecs_reviews.dto.OrderItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name="ECS-ORDER", configuration = FeignClientConfig.class)
public interface OrderService {
    @GetMapping("/api/order/getOrdersByCustomerId/{id}")
    ResponseEntity<List<OrderFinalDto>> getAllOrdersByCustomerId(@PathVariable("id") Integer customerId);

    @GetMapping("/api/cart/getCartsByProductId/{id}")
    ResponseEntity<List<CartDto>> getCartsByProductId(@PathVariable("id") Integer productId);

    @DeleteMapping("/api/cart/{id}")
    ResponseEntity<?> deleteCart(@PathVariable("id") Integer cartId);

    @GetMapping("/api/order/getOrderItemsByProductId/{id}")
    ResponseEntity<List<OrderItemDto>> getOrderItemsByProductId(@PathVariable("id") Integer productId);
}
