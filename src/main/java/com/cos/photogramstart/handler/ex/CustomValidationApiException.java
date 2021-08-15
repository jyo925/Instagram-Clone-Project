package com.cos.photogramstart.handler.ex;

import java.util.Map;

//    2.Ajax 또는 안드로이드 통신은 CMRespDto로 받는게 좋음
public class CustomValidationApiException extends RuntimeException {

    //객체를 구분할 때 사용(for JVM..)
    private static final long serialVersionUID = 1L;

//    private String message; Runtime 부모가 들고 있음

    private Map<String, String> errorMap;

    public CustomValidationApiException(String message) {
        super(message);
    }

    public CustomValidationApiException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }
}
