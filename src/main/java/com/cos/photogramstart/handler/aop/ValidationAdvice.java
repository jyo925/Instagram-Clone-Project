package com.cos.photogramstart.handler.aop;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

//adivce = 공통기능
@Component
@Aspect
@Slf4j
public class ValidationAdvice {

    //컨트롤러의 특정 메서드가 수행되기 전에 apiAdvice 또는 advice 메서드가 먼저 수행된다.
    //이때, ProceedingJoinPoint타입의 파라미터는 컨트롤러 메서드의 모든 곳(매개변수, 구현내용, 리턴값 등)에 접근할 수 있는 변수이다.
    //proceed()를 실행하여 컨트롤러 메서드를 실행한다.
    //이를 이용해서 Validation

    //execution(접근지시자 패키지 클래스 메서드 파라미터)
    @Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        log.info("web api 컨트롤러 ==================================");
        //매개변수 정보 출력
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {

                log.info("유효성 검사를 하는 함수입니다. : {}", arg);

                BindingResult bindingResult = (BindingResult) arg; //다운캐스팅

                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();
                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    throw new CustomValidationApiException("유효성 검사 실패", errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }

    @Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        log.info("web 컨트롤러 ==================================");
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {

                log.info("유효성 검사를 하는 함수입니다. : {}", arg);

                BindingResult bindingResult = (BindingResult) arg; //다운캐스팅

                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    throw new CustomValidationException("유효성 검사 실패", errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }
}
