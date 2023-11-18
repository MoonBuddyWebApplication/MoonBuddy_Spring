package com.project.moonbuddy.user.dto;

import com.project.moonbuddy.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {
    private String userId;
    private String pw;
    private String nickName;
    private int absorb;
    private int humidity;
    private int satisfaction;
    private int safety;
    private int price;

    public static UserDTO from(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .nickName(user.getNickName())
                .absorb(user.getCriterion().getAbsorb())
                .humidity(user.getCriterion().getHumidity())
                .satisfaction(user.getCriterion().getSatisfaction())
                .safety(user.getCriterion().getSafety())
                .price(user.getCriterion().getPrice())
                .build();
    }
}
