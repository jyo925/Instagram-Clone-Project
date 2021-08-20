package com.cos.photogramstart.web.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@NotBlank 빈 값 or null or 빈 공백(스페이스) 체크
//@NotEmpty 빈 값 or null 체크
//@NotNull null 체크

@Data
public class CommentDto {

    @NotBlank //빈값 or null or 공백문자 체크
    private String content;
    @NotNull
    private Integer imageId;

}
