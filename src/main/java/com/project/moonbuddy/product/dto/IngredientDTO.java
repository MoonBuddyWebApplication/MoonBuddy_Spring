package com.project.moonbuddy.product.dto;

import com.project.moonbuddy.product.model.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class IngredientDTO {
    private String title;
    private String description;

    public static IngredientDTO from(Ingredient ingredient) {
        return IngredientDTO.builder()
                .title(ingredient.getTitle())
                .description(ingredient.getDescription())
                .build();
    }
}
