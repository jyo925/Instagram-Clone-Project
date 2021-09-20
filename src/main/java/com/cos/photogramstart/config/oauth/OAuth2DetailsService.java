package com.cos.photogramstart.config.oauth;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

//페이스북 로그인 버튼을 클릭하면 페이스북 로그인 실행
//로그인(인증)이 되면 페이스북이 응답을 날려주는데
//응답을 받는 곳이 OAuth2DetailsService이며, 회원정보를 바로 받는다.
//로그인이 되면 loadUser() 자동으로 호출되며 이때 파라미터 userRequest에 회원정보가 담긴다.

@RequiredArgsConstructor
@Service
@Slf4j
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder; DI 사이클 문제

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("OAuth2DetailsService의 loadUser() 호출");

        OAuth2User oAuth2User = super.loadUser(userRequest); //super.loadUser가 userRequest 정보를 파싱해서 회원정보를 담아준다.
//        log.info("------------------------------------ {} ", oAuth2User.getAttributes());

        Map<String, Object> userInfo = oAuth2User.getAttributes();
        String name = (String) userInfo.get("name");
        String email = (String) userInfo.get("email");
        //username은 중복되지 않게 강제로 생성, id값 이용
        String username = "facebook_" + (String) userInfo.get("id");
        //password는 암호화 필수, 패스워드로 로그인하는 것이 아니기 때문에 필요 없음
        String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());

        User userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            //최초 로그인인 경우 ===> 회원 가입 진행
            User user = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .name(name)
                    .role("ROLE_USER")
                    .build();

            //OAuth 로그인인지 구분하려면 생성자 오버로딩 하는 방법도 있음(굳이 안해도 됨)
            return new PrincipalDetails(userRepository.save(user), oAuth2User.getAttributes());
        } else {
            //기존 회원인 경우
            return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
        }
    }
}
