package com.ecs.ecs_reviews.util;

import com.ecs.ecs_reviews.dto.*;
import com.ecs.ecs_reviews.feign.OrderService;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

@Setter
public class HelperFunctions {

    public static boolean isOrderExistsByProductId(int productId, OrderService orderService) {
        return Objects.requireNonNull(orderService.getOrderItemsByProductId(productId).getBody())
                .stream().anyMatch(orderItem -> orderItem.getProductId() == productId);
//        return !orders.stream().
//                filter((order) -> isProductExistsByProductId(productId, order.getProducts())).
//                toList().isEmpty();
    }

    public static boolean isProductExistsByProductId(int productId, List<ProductFinalDto> products) {
        return !products.
                stream().
                filter((product) -> product.getProductId() == productId).
                toList().isEmpty();
    }

    public static ResponseEntity<?> getResponseEntity(Object response) {
        if (Objects.equals(response, Constants.ProductNotFound)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found!");
        } else if (Objects.equals(response, Constants.CustomerNotFound)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Not Found!");
        } else if (Objects.equals(response, Constants.ProductQuantityExceeded)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ProductQuantities Exceeded!");
        } else if (Objects.equals(response, Constants.AddressNotFound)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address Not Found!");
        } else if (Objects.equals(response, Constants.OrderNotFound)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Not Found!");
        } else if (Objects.equals(response, Constants.CartNotFound)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart Not Found!");
        } else if (Objects.equals(response, Constants.UserNotFound)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found!");
        } else if (Objects.equals(response, Constants.ProductCategoryNotFound)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProductCategory Not Found!");
        } else if (Objects.equals(response, Constants.ProductBrandNotFound)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProductBrand Not Found!");
        } else if (Objects.equals(response, Constants.ProductReviewNotFound)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProductReview Not Found!");
        } else if (Objects.equals(response, HttpStatus.CONFLICT)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Entry!");
        } else if (Objects.equals(response, HttpStatus.BAD_REQUEST)) {
            return new ResponseEntity<>("Validation Failed/Bad Request!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
