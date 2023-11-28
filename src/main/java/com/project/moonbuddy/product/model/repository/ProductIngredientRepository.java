package com.project.moonbuddy.product.model.repository;

import com.project.moonbuddy.product.model.entity.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductIngredientRepository extends JpaRepository<ProductIngredient, Long> {
}
