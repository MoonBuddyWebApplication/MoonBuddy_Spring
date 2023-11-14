package com.project.moonbuddy.product.dto.response;

import com.project.moonbuddy.product.Mark;
import com.project.moonbuddy.product.dto.ReviewDTO;
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
    private String description;
    private String picture;
    private double absorption;
    private double humidity;
    private double satisfaction;
    private  double safety;
    private double price;
    private String image;
    private List<ReviewDTO.Response> reviewList;
    private List<Mark> markList;


}
