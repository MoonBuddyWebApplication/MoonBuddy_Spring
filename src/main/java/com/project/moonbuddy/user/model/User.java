package com.project.moonbuddy.user.model;

import com.project.moonbuddy.user.dto.UserDTO;
import com.project.moonbuddy.user.dto.UserInfoDTO;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String pw;
    private String nickName;
    @OneToOne
    @JoinColumn(name = "criterion_id")
    private Criterion criterion;

    public static User of(UserDTO userDTO, String pass, Criterion criterion) {
        return User.builder()
                .userId(userDTO.getUserId())
                .pw(pass)
                .nickName(userDTO.getNickName())
                .criterion(criterion)
                .build();
    }
    public User update(UserInfoDTO userInfoDTO, String pass) {
        return User.builder()
                .id(id)
                .userId(userInfoDTO.getUserId())
                .pw(pass)
                .nickName(userInfoDTO.getNickName())
                .criterion(criterion)
                .build();
    }
}
