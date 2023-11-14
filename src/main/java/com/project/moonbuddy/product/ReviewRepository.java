package com.project.moonbuddy.product;

import com.project.moonbuddy.product.Review;
import com.project.moonbuddy.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByProduct(Product product);
    List<Review> findByUser(User user);
}
