package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@ControllerAdvice //모든 예외를 낚아 챔
@RestController //예외에 대해 응답을 하기 위함
public class ControllerExceptionHandler {

    //RuntimeException이 발생하면 모두 이 함수가s 가로채서 실행
/*    @ExceptionHandler(CustomValidationException.class)
    public CMRespDto<?> validationException(CustomValidationException e) {
        return new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap());
    }*/

//    클라이언트에게 응답할 때는 아래와 같이 스크립트로 응답하는게 좋음
//    Ajax 또는 안드로이드 통신은 CMRespDto로 받는게 좋음
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e) {
        return Script.back(e.getErrorMap().toString());
    }
}
