package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder encoder;

    public UserProfileDto userProfile(int pageUserId, int principalId) {

        UserProfileDto dto = new UserProfileDto();

        User userEntity = userRepository.findById(pageUserId)
                .orElseThrow(() -> new CustomException("해당 프로필 페이지는 없는 페이지입니다."));


        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId == principalId); //1은 페이지 주인, -1은 주인 아님
        dto.setImageCount(userEntity.getImages().size());

        int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
        dto.setSubscribeCount(subscribeCount);
        dto.setSubscribeState(subscribeState == 1);

        return dto;
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
