package com.ecs.ecs_reviews.service.interfaces;

import com.ecs.ecs_reviews.dto.ProductReviewDto;

import java.util.List;

public interface IProductReviewService {
    ProductReviewDto getProductReviewById(Integer reviewId);

    List<ProductReviewDto> getAllProductReviews();

    List<ProductReviewDto> getProductReviewsByProductId(Integer productId);

    List<ProductReviewDto> getProductReviewsByCustomerId(Integer customerId);

    ProductReviewDto getProductReviewByCustomerIdAndProductId(Integer productId, Integer customerId);

    Object addProductReview(ProductReviewDto productReviewDto);

    Object updateProductReview(ProductReviewDto productReviewDto);

    boolean deleteProductReviewById(Integer reviewId);

    void deleteProductReviewsByProductId(Integer productId);

    void deleteProductReviewsByCustomerId(Integer customerId);

    boolean deleteProductReviewByProductIdAndCustomerId(Integer productId, Integer customerId);

    boolean isProductReviewExists(Integer reviewId);
}
