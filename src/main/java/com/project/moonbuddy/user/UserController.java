package com.project.moonbuddy.user;

import com.project.moonbuddy.auth.model.UserPrincipal;
import com.project.moonbuddy.user.dto.CriterionDTO;
import com.project.moonbuddy.user.dto.UserDTO;
import com.project.moonbuddy.user.model.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Operation(summary = "create user", description = "회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserDTO userDTO) {
        String status = userService.create(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
    @Operation(summary = "read user", description = "회원 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @GetMapping("/read")
    public ResponseEntity<UserDTO> read(@AuthenticationPrincipal UserPrincipal loginUser) {
        UserDTO userDTO = userService.read(loginUser);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }
    @Operation(summary = "update user", description = "회원 정보 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @PostMapping ("/update")
    public ResponseEntity<String> update(@AuthenticationPrincipal UserPrincipal loginUser, @RequestBody CriterionDTO criterionDTO) {
        String status = userService.update(loginUser, criterionDTO);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

}
