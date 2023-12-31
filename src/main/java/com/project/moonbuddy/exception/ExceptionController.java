package com.project.moonbuddy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e){
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();
        for(FieldError fieldError : e.getFieldErrors()){
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return response;
    }

    @ResponseBody
    @ExceptionHandler(MoonBuddyException.class)
    public ResponseEntity<ErrorResponse> marketException(MoonBuddyException e){
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation()).build();
        return ResponseEntity.status(statusCode).body(body);
    }
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> marketException(RuntimeException e){

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(400))
                .message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

}
