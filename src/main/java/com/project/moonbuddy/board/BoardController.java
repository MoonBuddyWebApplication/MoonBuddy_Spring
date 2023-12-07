package com.project.moonbuddy.board;

import com.project.moonbuddy.auth.model.UserPrincipal;
import com.project.moonbuddy.board.dto.request.BoardWrite;
import com.project.moonbuddy.board.dto.response.BoardResponse;
import com.project.moonbuddy.board.model.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Tag(name = "Board", description = "게시물 API")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @Operation(summary = "get posts", description = "게시물 가져오기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @GetMapping("/viewAll")
    public ResponseEntity viewAll(){
        List<BoardResponse> boardList = boardService.selectAll();
        return ResponseEntity.status(HttpStatus.OK).body(boardList);
    }

    @Operation(summary = "post", description = "게시물 작성하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @PostMapping("/post")
    public ResponseEntity post(@RequestBody BoardWrite boardWrite, @AuthenticationPrincipal UserPrincipal loginUser){
        log.info("boardWrite={}", boardWrite);
        String status = boardService.register(boardWrite, loginUser);

        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @Operation(summary = "delete", description = "게시물 삭제하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        String status = boardService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @Operation(summary = "update", description = "게시물 수정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @PostMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody BoardWrite boardWrite){
        String status = boardService.update(id, boardWrite);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @Operation(summary = "select post", description = "게시물 하나 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @GetMapping("/view/{id}")
    public ResponseEntity view(@PathVariable("id") Long id){
        BoardResponse boardResponse = boardService.select(id);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponse);
    }

    @Operation(summary = "like", description = "게시물 좋아요 누르기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @PostMapping("/like/{id}")
    public ResponseEntity like(@PathVariable("id") Long id, @AuthenticationPrincipal UserPrincipal loginUser){
        String status = boardService.like(id, loginUser);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }



}