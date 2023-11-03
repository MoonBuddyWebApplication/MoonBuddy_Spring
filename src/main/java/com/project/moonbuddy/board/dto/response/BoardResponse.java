package com.project.moonbuddy.board.dto.response;

import com.project.moonbuddy.board.dto.ReplyDTO;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class BoardResponse {
    private Long userId;
    private Long boardId;
    private String title;
    private String writer;
    private String content;
    private int likes;
    private String createdDate;
    private List<ReplyDTO.Response> replyList;
}
