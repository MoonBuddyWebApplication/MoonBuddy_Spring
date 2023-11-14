package com.project.moonbuddy.product;


import com.project.moonbuddy.product.dto.ReviewDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/review")
public class ReviewController {
    private ReviewService reviewService;

    @Operation(summary = "post", description = "후기 작성하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @PostMapping("/post")
    public ResponseEntity post(@RequestBody ReviewDTO.Request request){
        String status= reviewService.post(request);
        return ResponseEntity.status(HttpStatus.OK).body(status);

    }
    @Operation(summary = "delete", description = "후기 삭제하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        String status = reviewService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
}
