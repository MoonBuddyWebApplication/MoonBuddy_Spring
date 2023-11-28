package com.project.moonbuddy.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "product")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name" ,nullable = false)
    private String name;
    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "release_date", nullable = false)
    private String release_date;
    @Column(name = "description")
    private String description;
    @Column(name = "picture")
    private String picture;
    @Column(name = "absortion", nullable = false)
    private double absortion;
    @Column(name = "humidity", nullable = false)
    private double humidity;
    @Column(name = "satisfaction", nullable = false)
    private double satisfaction;
    @Column(name = "safety", nullable = false)
    private double safety;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name="image")
    private String image;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 후기 정렬
    private List<Review> reviewList;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 후기 정렬
    private List<Mark> markList;
}
