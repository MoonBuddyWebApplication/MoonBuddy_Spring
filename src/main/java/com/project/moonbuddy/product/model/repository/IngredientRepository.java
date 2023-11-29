package com.project.moonbuddy.product.model.repository;

import com.project.moonbuddy.product.model.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
