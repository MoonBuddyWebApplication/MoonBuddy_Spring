package com.project.moonbuddy.board.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class BoardWrite {
    private String title;
    private Long userId;
    private String content;
}
