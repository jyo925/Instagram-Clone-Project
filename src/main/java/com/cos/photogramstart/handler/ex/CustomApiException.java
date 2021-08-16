package com.cos.photogramstart.handler.ex;

//Validation 이외 오류들(SqlException 등등)
public class CustomApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomApiException(String message) {
        super(message);
    }

}
