package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public User userProfile(int userId) {
        User userEntity = userRepository.findById(userId).orElseThrow(() -> new CustomException("해당 프로필 페이지는 없는 페이지입니다."));
        return userEntity;
    }

    @Transactional
    public User update(int id, User user) {

        //1. 영속화
        //Optional 타입으로 리턴 받는데
        //1.get()은 무조건 찾았다고 가정
        //2.orElseThrow() 못찾은 경우 예외 발생
        //예외 처리는 추후 처리할 예정으로 우선 get으로 진행
 /*       User userEntity = userRepository.findById(id).orElseThrow(
                new Supplier<IllegalArgumentException>() {
                    @Override
                    public IllegalArgumentException get() {
                        return new IllegalArgumentException("찾을 수 없는 아이디입니다.");
                    }
                }
        );
*/
        User userEntity = userRepository.findById(id).orElseThrow(() -> new CustomValidationApiException("찾을 수 없는 아이디입니다."));

        //2. 영속화된 오브젝트를 수정하면 DB 자동 update(더티채킹)
        userEntity.setName(user.getName());


        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        userEntity.setPassword(encPassword);

        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity;
    }
}
