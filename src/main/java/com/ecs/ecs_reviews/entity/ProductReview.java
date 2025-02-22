package com.ecs.ecs_reviews.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "product_review")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "product_rating")
    private Integer productRating;

    @Column(name = "review_headline")
    private String reviewHeadline;

    @Column(name = "product_review")
    private String productReview;

    @Column(name = "review_date")
    private LocalDateTime reviewDate;

}
