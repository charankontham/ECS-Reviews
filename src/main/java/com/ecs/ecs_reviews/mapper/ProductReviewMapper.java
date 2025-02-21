package com.ecs.ecs_reviews.mapper;

import com.ecs.ecs_reviews.dto.ProductReviewDto;
import com.ecs.ecs_reviews.entity.ProductReview;

public class ProductReviewMapper {
    public static ProductReview mapToProductReview(ProductReviewDto productReviewDto) {
        return new ProductReview(
                productReviewDto.getReviewId(),
                productReviewDto.getProductId(),
                productReviewDto.getCustomerId(),
                productReviewDto.getCustomerName(),
                productReviewDto.getProductRating(),
                productReviewDto.getReviewHeadline(),
                productReviewDto.getProductReview(),
                productReviewDto.getReviewDate()
        );
    }

    public static ProductReviewDto mapToProductReviewDto(ProductReview productReview) {
        return new ProductReviewDto(
                productReview.getReviewId(),
                productReview.getProductId(),
                productReview.getCustomerId(),
                productReview.getCustomerName(),
                productReview.getProductRating(),
                productReview.getReviewHeadline(),
                productReview.getProductReview(),
                productReview.getReviewDate()
        );
    }
}
