package com.ecs.ecs_reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductReviewDto {
    private Integer reviewId;
    private Integer productId;
    private Integer customerId;
    private String productReview;
    private float productRating;
}
