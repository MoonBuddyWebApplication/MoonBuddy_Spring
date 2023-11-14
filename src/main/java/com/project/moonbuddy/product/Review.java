package com.project.moonbuddy.product;

import com.project.moonbuddy.domain.BaseTimeEntity;
import com.project.moonbuddy.product.Product;
import com.project.moonbuddy.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="review")
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column(name="review_content",nullable = false)
    private String reviewContetent;
    public void update(String review){this.reviewContetent=review;}
}
