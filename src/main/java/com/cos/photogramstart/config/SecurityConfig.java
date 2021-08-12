package com.cos.photogramstart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity //SecurityConfig 이 파일로 시큐리티를 활성화 시킨다.
@Configuration //IOC
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super 삭제 -> 기존 시큐리티가 가지고 있는 기능이 모두 비활성화 됨
        //우리가 원하는대로 설정 셋팅
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**")
                .authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/signin")
                .defaultSuccessUrl("/");
    }
}
