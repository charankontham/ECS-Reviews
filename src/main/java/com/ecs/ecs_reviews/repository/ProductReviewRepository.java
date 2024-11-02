package com.ecs.ecs_reviews.repository;

import com.ecs.ecs_reviews.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {

    List<ProductReview> findAllByProductId(int productId);

    List<ProductReview> findAllByCustomerId(int customerId);

    Optional<ProductReview> findByProductIdAndCustomerId(int productId, int customerId);

    boolean existsByProductIdAndCustomerId(int productId, int customerId);

    void deleteByProductIdAndCustomerId(int productId, int customerId);

    void deleteByProductId(int productId);

    void deleteByCustomerId(int customerId);
}
