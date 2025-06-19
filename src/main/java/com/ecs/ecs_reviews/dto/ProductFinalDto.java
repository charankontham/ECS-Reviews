package com.ecs.ecs_reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductFinalDto {
    private Integer productId;
    private String productName;
    private ProductBrandDto brand;
    private ProductCategoryDto productCategory;
    private String productDescription;
    private Float productPrice;
    private Integer productQuantity;
    private String productImage;
    private String productColor;
    private Float productWeight;
    private LocalDateTime dateAdded;
    private LocalDateTime dateModified;
    private String productDimensions;
    private String productCondition;
}
