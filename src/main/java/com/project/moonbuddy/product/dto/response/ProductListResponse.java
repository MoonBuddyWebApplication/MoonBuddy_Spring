package com.project.moonbuddy.product.dto.response;

import com.project.moonbuddy.product.model.entity.Product;
import com.project.moonbuddy.user.model.User;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ProductListResponse {
    private Long product_id;
    private String name;
    private String category;
    private String brand;
    private String release_date;
    private String product_image;
    private int product_price;
    private double score;

    public static ProductListResponse of(Product v, User user) {
        return ProductListResponse.builder()
                .product_id(v.getId())
                .name(v.getName())
                .brand(v.getBrand())
                .product_image(v.getProduct_image())
                .product_price(v.getProduct_price())
                .score(v.getScore(user.getCriterion()))
                .build();
    }
}
