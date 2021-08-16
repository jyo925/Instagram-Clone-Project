package com.cos.photogramstart.web.dto.image;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

//사진 업로드 시 받아야 할 것들
@Data
public class ImageUploadDto {
    
//    @NotBlank multifile type은 이 어노테이션이 지원이 안 됨
    private MultipartFile file;

    private String caption;

    public Image toEntity(String postImageUrl, User user) {
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(user)
                .build();
    }


}
