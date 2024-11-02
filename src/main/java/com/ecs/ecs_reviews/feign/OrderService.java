package com.ecs.ecs_reviews.feign;

import com.ecs.ecs_reviews.dto.CartDto;
import com.ecs.ecs_reviews.dto.OrderFinalDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient("ECS-ORDER")
public interface OrderService {
    @GetMapping("/api/order/getOrdersByCustomerId/{id}")
    ResponseEntity<List<OrderFinalDto>> getAllOrdersByCustomerId(@PathVariable("id") int customerId);

    @GetMapping("/api/cart/getCartsByProductId/{id}")
    ResponseEntity<List<CartDto>> getCartsByProductId(@PathVariable("id") int productId);

    @DeleteMapping("/api/cart/{id}")
    ResponseEntity<?> deleteCart(@PathVariable("id") int cartId);
}
