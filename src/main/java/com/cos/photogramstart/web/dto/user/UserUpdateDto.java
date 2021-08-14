package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

@Data
public class UserUpdateDto {

    private String name;
    private String password;
    private String website;
    private String bio; //자기소개
    private String phone;
    private String gender;

    //필수로 받아야 하는 필드가 있고 아닌 필드가 있기 때문에
    //코드 수정이 필요함
    public User toEntity() {
        return User.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
