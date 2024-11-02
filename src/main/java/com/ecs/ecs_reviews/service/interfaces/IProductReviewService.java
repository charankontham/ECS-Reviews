package com.ecs.ecs_reviews.service.interfaces;

import com.ecs.ecs_reviews.dto.ProductReviewDto;
import java.util.List;

public interface IProductReviewService {

    ProductReviewDto getProductReviewById(int reviewId);

    List<ProductReviewDto> getAllProductReviews();

    List<ProductReviewDto> getProductReviewsByProductId(int productId);

    List<ProductReviewDto> getProductReviewsByCustomerId(int customerId);

    ProductReviewDto getProductReviewByCustomerIdAndProductId(int productId, int customerId);

    Object addProductReview(ProductReviewDto productReviewDto);

    Object updateProductReview(ProductReviewDto productReviewDto);

    boolean deleteProductReviewById(int reviewId);

    void deleteProductReviewsByProductId(int productId);

    void deleteProductReviewsByCustomerId(int customerId);

    boolean deleteProductReviewByProductIdAndCustomerId(int productId, int customerId);

    boolean isProductReviewExists(int reviewId);
}
