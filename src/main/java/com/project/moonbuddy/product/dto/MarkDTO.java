package com.project.moonbuddy.product.dto;

import com.project.moonbuddy.product.Mark;
import com.project.moonbuddy.product.Review;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class MarkDTO {

    @RequiredArgsConstructor
    @Getter
    public static class Response {
        private Long makrId;
        private String name;
        private String explanation;
        private String image;

        public Response(Mark mark){
                this.makrId=getMakrId();
                this.name=getName();
                this.explanation=getExplanation();
                this.image=getImage();

             }


    }


}
