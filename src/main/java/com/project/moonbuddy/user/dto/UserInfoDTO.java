package com.project.moonbuddy.user.dto;

import com.project.moonbuddy.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserInfoDTO {
    private String userId;
    private String pw;
    private String nickName;
}
