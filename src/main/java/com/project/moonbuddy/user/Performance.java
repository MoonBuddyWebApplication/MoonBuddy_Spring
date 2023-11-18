//package com.project.moonbuddy.user;
//
//import com.project.moonbuddy.user.dto.ProductDTO;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Getter
//@Entity
//public class Performance {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private int absorb;
//    private int humidity;
//    private int satisfaction;
//    private int safety;
//    private int price;
//    public Performance from(ProductDTO productDTO) {
//        return Performance.builder()
//                .absorb(productDTO.getAbsorb())
//                .humidity(productDTO.getHumidity())
//                .satisfaction(productDTO.getSatisfaction())
//                .safety(productDTO.getSafety())
//                .price(productDTO.getPrice())
//                .build();
//    }
//    public double getScore(Criterion criterion) {
//        return absorb * criterion.getAbsorb()
//                + humidity * criterion.getHumidity()
//                + satisfaction * criterion.getSatisfaction()
//                + safety * criterion.getSafety()
//                + price * criterion.getPrice();
//    }
//}
