package com.project.moonbuddy.product.model.repository;
import com.project.moonbuddy.product.model.entity.Review;
import com.project.moonbuddy.product.model.entity.Product;
import com.project.moonbuddy.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByProduct(Product product);
    List<Review> findByUser(User user);
}
