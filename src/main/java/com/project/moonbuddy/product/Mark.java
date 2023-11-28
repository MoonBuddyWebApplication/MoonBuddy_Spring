package com.project.moonbuddy.product;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="mark")
@Setter
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="name",nullable = false)
    private String name;
    @Column(name="explanation",nullable = false)
    private String explanation;
    @Column(name="image",nullable = false)
    private String image;


    @Override
    public String toString() {
        return "Mark{id=" + id + ", name='" + name + "', explanation='" + explanation + "', image='" + image + "'}";
    }

}
