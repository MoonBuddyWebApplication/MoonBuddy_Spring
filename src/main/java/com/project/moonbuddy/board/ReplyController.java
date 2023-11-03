package com.project.moonbuddy.board;

import com.project.moonbuddy.board.dto.ReplyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Reply", description = "Reply API")
@Slf4j
@CrossOrigin(origins = "http://172.16.65.251:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/reply")
public class ReplyController {
    private ReplyService replyService;
    @Operation(summary = "post", description = "댓글 작성하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @PostMapping("/post")
    public ResponseEntity post(@RequestBody ReplyDTO.Request request){
        //SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        //User user = userService.findUser(sessionUser);
        String status = replyService.post(request);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
    @Operation(summary = "delete", description = "댓글 삭제하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        String status = replyService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

}