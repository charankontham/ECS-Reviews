package com.ecs.ecs_reviews.mapper;

import com.ecs.ecs_reviews.dto.ProductReviewDto;
import com.ecs.ecs_reviews.entity.ProductReview;

public class ProductReviewMapper {
    public static ProductReview mapToProductReview(ProductReviewDto productReviewDto) {
        return new ProductReview(
                productReviewDto.getReviewId(),
                productReviewDto.getProductId(),
                productReviewDto.getCustomerId(),
                productReviewDto.getProductRating(),
                productReviewDto.getReviewHeadline(),
                productReviewDto.getProductReview()
        );
    }

    public static ProductReviewDto mapToProductReviewDto(ProductReview productReview) {
        return new ProductReviewDto(
                productReview.getReviewId(),
                productReview.getProductId(),
                productReview.getCustomerId(),
                productReview.getProductRating(),
                productReview.getReviewHeadline(),
                productReview.getProductReview()
        );
    }
}
