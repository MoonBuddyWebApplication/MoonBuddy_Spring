package com.project.moonbuddy.board.model;

import com.project.moonbuddy.auth.model.UserPrincipal;
import com.project.moonbuddy.board.dto.ReplyDTO.Request;
import com.project.moonbuddy.user.model.User;
import com.project.moonbuddy.user.model.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class ReplyService {
    private ReplyRepository replyRepository;
    private BoardRepository boardRepository;
    private UserRepository userRepository;

    public String post(Request request, UserPrincipal loginUser) {
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(()-> new RuntimeException("존재하지 않는 게시글입니다."));
        User user = userRepository.findById(getLoginUserId(loginUser))
                .orElseThrow(()-> new RuntimeException("존재하지 않는 사용자입니다."));
        Reply reply = request.toEntity(user, board);
        replyRepository.save(reply);
        return "SUCCESS";
    }

    public String delete(Long id) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(()->new RuntimeException("존재하지 않는 게시글입니다."));
        replyRepository.delete(reply);
        return "SUCCESS";
    }

    public Long getLoginUserId(UserPrincipal loginUser) {
        if (loginUser == null) {
            return 13L;
        }
        return loginUser.getId();
    }
}
