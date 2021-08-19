package com.cos.photogramstart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

//Web 설정 파일
//스프링 기능 확장은 WebMvcConfigurer를 구현하면 된다.
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.path}")
    private String uploadFolder;

    //jsp상에서 이미지 파일 경로를 단축해서 사용하고 실제 경로는 여기서 변경
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry
                .addResourceHandler("/upload/**") //jsp 페이지에서 /upload/** 주소 패턴이 나올 때 발동
                .addResourceLocations("file:///" + uploadFolder)
                .setCachePeriod(60 * 10 * 6) //60초 * 10 * 6 = 1시간 동안 이미지를 캐싱
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
