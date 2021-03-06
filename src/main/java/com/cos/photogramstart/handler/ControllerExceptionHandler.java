package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //모든 예외를 낚아 챔
@RestController
public class ControllerExceptionHandler {

/*
//RuntimeException이 발생하면 모두 이 함수가 가로채서 실행
    @ExceptionHandler(CustomValidationException.class)
    public CMRespDto<?> validationException(RuntimeException e) {
        return new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap());
    }
*/

    //    1.클라이언트에게 응답할 때는 아래와 같이 스크립트로 응답
    //    2.Ajax 또는 Android 통신은 CMRespDto로 응답
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e) {

        if (e.getErrorMap() == null) {
            return Script.back(e.getMessage());
        } else {
            return Script.back(e.getErrorMap().toString());
        }
    }

    //상태코드가 있어야 ajax에서 성공과 실패를 구분
    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<CMRespDto<?>> validationApiException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    //Data 리턴 컨트롤러
    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<CMRespDto<?>> apiException(CustomApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
    
    //HTML 리턴 컨트롤러
    @ExceptionHandler(CustomException.class)
    public String exception(CustomException e) {
        return Script.back(e.getMessage());
    }
}
