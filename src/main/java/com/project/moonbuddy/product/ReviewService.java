package com.project.moonbuddy.product;

import com.project.moonbuddy.product.dto.ReviewDTO;
import com.project.moonbuddy.user.model.User;
import com.project.moonbuddy.user.model.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@AllArgsConstructor
@Transactional
@Slf4j
@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;


    public String post(ReviewDTO.Request request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(()-> new RuntimeException("존재하지 않는 상품입니다."));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new RuntimeException("존재하지 않는 사용자입니다."));
        Review review = request.toEntity(user,product);
        reviewRepository.save(review);
        return "SUCCESS";
    }
    public String delete(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(()->new RuntimeException("존재하지 않는 상품입니다."));
        reviewRepository.delete(review);
        return "SUCCESS";
    }


}
