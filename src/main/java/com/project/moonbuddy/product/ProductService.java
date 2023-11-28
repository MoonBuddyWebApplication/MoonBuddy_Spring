package com.project.moonbuddy.product;
import com.project.moonbuddy.product.dto.ReviewDTO;
import com.project.moonbuddy.product.dto.response.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Slf4j
@Service
public class ProductService {
    private  ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
            this.productRepository=productRepository;

    }

    public ProductResponse select(Long id){
        Product product=productRepository.findById(id).orElseThrow(()->new RuntimeException("존재하지 않는 상품입니다."));
        List<ReviewDTO.Response> reviewlist = new ArrayList<>();
        product.getReviewList().forEach(v->
                reviewlist.add(new ReviewDTO.Response(v)));
        List<Mark> marklist = new ArrayList<>();
        product.getMarkList().forEach(v->
                marklist.add(new Mark()));

        ProductResponse productResponse=ProductResponse.builder()
                .product_id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .brand(product.getBrand())
                .release_date(product.getRelease_date())
                .description(product.getDescription())
                .picture(product.getPicture())
                .absorption(product.getAbsortion())
                .humidity(product.getHumidity())
                .satisfaction(product.getSatisfaction())
                .safety(product.getSafety())
                .price(product.getPrice())
                .reviewList(reviewlist)
                .markList(marklist)
                .image(product.getImage())
                .build();

    return productResponse;
    }

    public List<ProductResponse> selectAll() {
        List<Product> productList =productRepository.findAll();
        List<ProductResponse> result = new ArrayList<>();
        productList.forEach(v->{
            result.add(select(v.getId()));
        });

        return result;
    }
}
