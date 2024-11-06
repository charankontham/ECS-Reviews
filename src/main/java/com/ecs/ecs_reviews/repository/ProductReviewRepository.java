package com.ecs.ecs_reviews.repository;

import com.ecs.ecs_reviews.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {

    List<ProductReview> findAllByProductId(Integer productId);

    List<ProductReview> findAllByCustomerId(Integer customerId);

    Optional<ProductReview> findByProductIdAndCustomerId(Integer productId, Integer customerId);

    boolean existsByProductIdAndCustomerId(Integer productId, Integer customerId);

    @Transactional
    void deleteByProductIdAndCustomerId(Integer productId, Integer customerId);

    @Transactional
    void deleteByProductId(Integer productId);

    @Transactional
    void deleteByCustomerId(Integer customerId);
}
