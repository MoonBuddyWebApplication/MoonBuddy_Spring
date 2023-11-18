package com.project.moonbuddy.user.model;

import com.project.moonbuddy.user.dto.CriterionDTO;
import com.project.moonbuddy.user.dto.UserDTO;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Criterion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int absorb;
    private int humidity;
    private int satisfaction;
    private int safety;
    private int price;
    @OneToOne(mappedBy = "criterion")
    private User user;

    public static Criterion from(UserDTO userDTO) {
        return Criterion.builder()
                .absorb(userDTO.getAbsorb())
                .humidity(userDTO.getHumidity())
                .satisfaction(userDTO.getSatisfaction())
                .safety(userDTO.getSafety())
                .price(userDTO.getPrice())
                .build();
    }

    public Criterion update(CriterionDTO criterionDTO) {
        this.absorb = criterionDTO.getAbsorb();
        this.humidity = criterionDTO.getHumidity();
        this.satisfaction = criterionDTO.getSatisfaction();
        this.safety = criterionDTO.getSafety();
        this.price = criterionDTO.getPrice();
        return this;
    }
}
