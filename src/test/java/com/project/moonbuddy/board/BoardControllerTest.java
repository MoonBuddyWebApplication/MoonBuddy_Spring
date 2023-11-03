package com.project.moonbuddy.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.moonbuddy.board.dto.request.BoardWrite;
import com.project.moonbuddy.user.User;
import com.project.moonbuddy.user.UserRepository;
import com.project.moonbuddy.user.config.oauth.dto.SessionUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시글 등록 성공")
    void registertest() throws Exception{
        // given
        User user = userRepository.findById(1L)
                .orElseThrow(()->new UsernameNotFoundException("존재하지 않는 사용자입니다."));
        SessionUser sessionUser = new SessionUser(user);
        BoardWrite boardWrite = BoardWrite.builder()
                .title("제목")
                .content("내용")
                .build();
        String json = objectMapper.writeValueAsString(boardWrite);
        log.info("json={}", json);

        //expected
        mockMvc.perform(post("/board/register")
                        .sessionAttr("user", user)
                        .contentType(APPLICATION_JSON)
                        .content(json))
                // 가상의 요청을 처리한다.
                // return 값으로 ResultActions 객체를 받으며, 이 객체는 리턴값을 검증하고 확인할 수 있는 andExpect()를 처리한다.
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 응답을 검증하는 역할을 한다.
                // 상태검증(status()), 응답 본문 내용 검증(content()), 스프링mvc모델 상태 검증(model())
                .andDo(MockMvcResultHandlers.print());
        // 전체 결과 출력(print())

    }


}