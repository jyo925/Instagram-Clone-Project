package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${file.path}")
    private String uploadFolder;


    @Transactional(readOnly = true)
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

        //회원 프로필 페이지 이미지에 좋아요 카운트 표시
        userEntity.getImages().forEach(i -> {
            i.setLikeCount(i.getLikes().size());
        });

        //이미지 정보 역순으로 = 최신 사진이 가장 먼저 보이도록
        //그런데 여기서 변경해봤자 지연로딩이기 때문에 결국 뷰단에서 getImage()가 호출될때 이 로직을 한 번 또 탐
        //JPA가 자동으로 쿼리를 수행하는거라....desc순으로 어떻게 보여지게 해야할지 궁금함
        List<Image> images = dto.getUser().getImages();
        images.forEach(i -> {
            log.info(String.valueOf(i.getId()));
        });
        log.info("-----------------------역순으로 변경 ----------------------------");
        Collections.reverse(images);
        images.forEach(i -> {
            log.info(String.valueOf(i.getId()));
        });
        dto.getUser().setImages(images);

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

    @Transactional
    public User userProfileUpdate(int principalId, MultipartFile profileImageFile) {
        //사진 서버에 저장
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + profileImageFile.getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);
        try {
            Files.write(imageFilePath, profileImageFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //사진 DB 저장
        User userEntity = userRepository.findById(principalId)
                .orElseThrow(() -> new CustomApiException("프로필 이미지를 변경할 유저를 찾을 수 없습니다."));

        userEntity.setProfileImageUrl(imageFileName);
        return userEntity;
    }
}
