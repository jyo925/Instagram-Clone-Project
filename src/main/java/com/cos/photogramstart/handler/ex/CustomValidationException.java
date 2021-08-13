package com.cos.photogramstart.handler.ex;

import lombok.Getter;

import java.util.Map;


public class CustomValidationException extends RuntimeException {

    //객체를 구분할 때 사용(for JVM..)
    private static final long serialVersionUID = 1L;

//    private String message; Runtime 부모가 들고 있음

    private Map<String, String> errorMap;

    public CustomValidationException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }
}
