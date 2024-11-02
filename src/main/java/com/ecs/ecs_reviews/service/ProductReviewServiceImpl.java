package com.ecs.ecs_reviews.service;

import com.ecs.ecs_reviews.dto.ProductReviewDto;
import com.ecs.ecs_reviews.entity.ProductReview;
import com.ecs.ecs_reviews.exception.ResourceNotFoundException;
import com.ecs.ecs_reviews.feign.CustomerService;
import com.ecs.ecs_reviews.feign.OrderService;
import com.ecs.ecs_reviews.feign.ProductService;
import com.ecs.ecs_reviews.mapper.ProductReviewMapper;
import com.ecs.ecs_reviews.repository.ProductReviewRepository;
import com.ecs.ecs_reviews.service.interfaces.IProductReviewService;
import com.ecs.ecs_reviews.util.Constants;
import com.ecs.ecs_reviews.util.HelperFunctions;
import com.ecs.ecs_reviews.validations.ProductReviewValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductReviewServiceImpl implements IProductReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @Override
    public ProductReviewDto getProductReviewById(int reviewId) {
        ProductReview productReview = productReviewRepository.findById(reviewId).
                orElseThrow(() -> new ResourceNotFoundException("ProductReview Not Found!"));
        return ProductReviewMapper.mapToProductReviewDto(productReview);
    }

    @Override
    public List<ProductReviewDto> getAllProductReviews() {
        List<ProductReview> productReviews = productReviewRepository.findAll();
        return productReviews.stream().map(ProductReviewMapper::mapToProductReviewDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductReviewDto> getProductReviewsByProductId(int productId) {
        List<ProductReview> productReviews = productReviewRepository.findAllByProductId(productId);
        return productReviews.stream().map(ProductReviewMapper::mapToProductReviewDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductReviewDto> getProductReviewsByCustomerId(int customerId) {
        List<ProductReview> productReviews = productReviewRepository.findAllByCustomerId(customerId);
        return productReviews.stream().map(ProductReviewMapper::mapToProductReviewDto).collect(Collectors.toList());
    }

    @Override
    public ProductReviewDto getProductReviewByCustomerIdAndProductId(int productId, int customerId) {
        ProductReview productReview = productReviewRepository.
                findByProductIdAndCustomerId(productId, customerId).
                orElseThrow(() -> new ResourceNotFoundException("ProductReview Not Found!"));
        return ProductReviewMapper.mapToProductReviewDto(productReview);
    }

    @Override
    public Object addProductReview(ProductReviewDto productReviewDto) {
        boolean productReviewIdExists = Objects.nonNull(productReviewDto.getReviewId());
        if (productReviewIdExists) {
            if(productReviewRepository.existsById(productReviewDto.getReviewId())){
                return HttpStatus.CONFLICT;
            }
        }
        try {
            return validateAndSaveProductReview(productReviewDto);
        } catch (DataIntegrityViolationException e) {
            System.out.println("DataIntegrity Exception message : " + e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    public Object updateProductReview(ProductReviewDto productReviewDto) {
        boolean productReviewExists = productReviewRepository.existsById(productReviewDto.getReviewId());
        if (productReviewExists) {
            try {
                return validateAndSaveProductReview(productReviewDto);
            }catch (DataIntegrityViolationException e) {
                System.out.println("Exception message : " + e.getMessage());
                return HttpStatus.CONFLICT;
            }
        }
        return Constants.ProductReviewNotFound;
    }

    @Override
    public boolean deleteProductReviewById(int reviewId) {
        boolean productReviewExists = productReviewRepository.existsById(reviewId);
        if (productReviewExists) {
            productReviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }

    @Override
    public void deleteProductReviewsByProductId(int productId) {
        productReviewRepository.deleteByProductId(productId);
    }

    @Override
    public void deleteProductReviewsByCustomerId(int customerId) {
        productReviewRepository.deleteByCustomerId(customerId);
    }

    @Override
    public boolean deleteProductReviewByProductIdAndCustomerId(int productId, int customerId) {
        boolean productReviewExists = productReviewRepository.existsByProductIdAndCustomerId(productId, customerId);
        if (productReviewExists) {
            productReviewRepository.deleteByProductIdAndCustomerId(productId, customerId);
            return true;
        }
        return false;
    }

    @Override
    public boolean isProductReviewExists(int reviewId) {
        return productReviewRepository.existsById(reviewId);
    }

    private Object validateAndSaveProductReview(ProductReviewDto productReviewDto) throws DataIntegrityViolationException {
        boolean customerExists = customerService.getCustomerById(productReviewDto.getCustomerId()).getStatusCode()==HttpStatus.OK;
        boolean productExists = productService.getProductById(productReviewDto.getProductId()).getStatusCode()==HttpStatus.OK;
        boolean orderExists = HelperFunctions.isOrderExistsByProductId(
                productReviewDto.getProductId(),
                Objects.requireNonNull(orderService.getAllOrdersByCustomerId(productReviewDto.getCustomerId()).getBody())
        );
        if (!ProductReviewValidation.validateProductReview(productReviewDto)) {
            return HttpStatus.BAD_REQUEST;
        } else if (!customerExists) {
            return Constants.CustomerNotFound;
        } else if (!productExists) {
            return Constants.ProductNotFound;
        } else if (!orderExists) {
            return Constants.OrderNotFound;
        } else {
                ProductReview productReview = productReviewRepository.save(ProductReviewMapper.mapToProductReview(productReviewDto));
                return ProductReviewMapper.mapToProductReviewDto(productReview);
        }
    }
}
