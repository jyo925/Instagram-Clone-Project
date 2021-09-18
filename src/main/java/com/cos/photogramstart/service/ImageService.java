package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void imageUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {

        UUID uuid = UUID.randomUUID();
        //uuid + 실제 파일명
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);
//        System.out.println("imageFilePath = " + imageFilePath);
//        System.out.println(imageUploadDto.getFile());
        //통신 or 입출력이 일어나는 경우는 예외가 발생할 수 있다.
        //파일이 없거나 존재하지 않는 경우 컴파일로 잡을 수 없음 -> 런타임에 예외가 발생하기 때문에 예외처리 필수
        try {
            //path, 실제이미지파일(byte), 옵션값 생략
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //DB 저장
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        imageRepository.save(image);
    }


    @Transactional(readOnly = true)
    public Page<Image> imageStory(int principalId, Pageable pageable) {
        //스토리 정보
        Page<Image> images = imageRepository.mStory(principalId, pageable);

        //좋아요 정보
        //이미지를 가져올 때 좋아요 정보도 들고오려면 양방향 매핑으로
        //해당 이미지에 좋아요 정보를 모두 들고와서 현재 로그인한 사용자가 좋아요를 눌렀는지 확인
        images.forEach(i -> {

            i.setLikeCount(i.getLikes().size());
            i.getLikes().forEach(like -> {
                if (like.getUser().getId() == principalId) {
                    i.setLikeState(true);
                }
            });
        });
        return images;
    }

    @Transactional(readOnly = true)
    public List<Image> popularImages() {
        return imageRepository.mPopular();
    }
}
