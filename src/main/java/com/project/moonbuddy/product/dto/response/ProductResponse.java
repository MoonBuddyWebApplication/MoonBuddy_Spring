package com.project.moonbuddy.product.dto.response;

//import com.project.moonbuddy.product.model.entity.Mark;
import com.project.moonbuddy.product.dto.IngredientDTO;
import com.project.moonbuddy.product.dto.MarkDTO;
import com.project.moonbuddy.product.dto.ReviewDTO;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data

public class ProductResponse {
    private Long product_id;
    private String name;
    private String category;
    private String brand;
    private String release_date;
    private String product_image;
    private String product_info_image;
    private double score;
    private List<ReviewDTO.Response> reviewList;
    private List<MarkDTO.Response> markList;
    private List<IngredientDTO> ingredientList;
}
