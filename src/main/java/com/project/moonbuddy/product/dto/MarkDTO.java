package com.project.moonbuddy.product.dto;

import com.project.moonbuddy.product.model.entity.Mark;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class MarkDTO {

    @RequiredArgsConstructor
    @Getter
    public static class Response {
        private Long markId;
        private String name;
        private String explanation;
        private String image;

        public Response(Mark mark){
                this.markId = mark.getId();
                this.name = mark.getName();
                this.explanation = mark.getExplanation();
                this.image = mark.getImage();

             }


    }


}
