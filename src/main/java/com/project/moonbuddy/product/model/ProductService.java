package com.project.moonbuddy.product.model;
import com.project.moonbuddy.auth.model.UserPrincipal;
import com.project.moonbuddy.product.dto.IngredientDTO;
import com.project.moonbuddy.product.dto.MarkDTO;
import com.project.moonbuddy.product.dto.ReviewDTO;
import com.project.moonbuddy.product.dto.response.ProductResponse;
import com.project.moonbuddy.product.model.entity.Product;
import com.project.moonbuddy.product.model.repository.ProductRepository;
import com.project.moonbuddy.user.model.User;
import com.project.moonbuddy.user.model.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Slf4j
@Service
public class ProductService {
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
            this.productRepository = productRepository;
            this.userRepository = userRepository;
    }

    public ProductResponse select(Long id, UserPrincipal loginUser){
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("존재하지 않는 상품입니다."));
        Long userId = getLoginUserId(loginUser);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));
        List<ReviewDTO.Response> reviewlist = new ArrayList<>();
        product.getReviewList().forEach(v->
                reviewlist.add(new ReviewDTO.Response(v)));

        List<MarkDTO.Response> marklist=new ArrayList<>();
        product.getMarkList().forEach(v->
                marklist.add(new MarkDTO.Response(v)));

        List<IngredientDTO> ingredientDTOS = new ArrayList<>();
        product.getIngredients().forEach(v -> {
            ingredientDTOS.add(IngredientDTO.from(v.getIngredient()));
        });
        return ProductResponse.builder()
                .product_id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .brand(product.getBrand())
                .release_date(product.getRelease_date())
                .product_image(product.getProduct_image())
                .product_info_image(product.getProduct_info_image())
                .product_price(product.getProduct_price())
                .score(product.getScore(user.getCriterion()))
                .reviewList(reviewlist)
                .markList(marklist)
                .ingredientList(ingredientDTOS)
                .build();
    }

    public List<ProductResponse> selectAll(UserPrincipal loginUser) {
        List<Product> productList = productRepository.findAll();
        List<ProductResponse> result = new ArrayList<>();
        productList.forEach(v->{
            result.add(select(v.getId(), loginUser));
        });

        return result;
    }

    public Long getLoginUserId(UserPrincipal loginUser) {
        if (loginUser == null) {
            return 13L;
        }
        return loginUser.getId();
    }
}
