package com.cos.photogramstart.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

//"유효성 검사 실패", errorMap 둘다 리턴하기 위한 DTO
//재사용하기 위해 제네릭을 사용
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CMRespDto<T> {

    private int code; //1(성공), -1(실패)
    private String message;
//    private Map<String, String> errorMap;
    private T data;

}
