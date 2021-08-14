package com.cos.photogramstart.web.api;

import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserApiController {

    @PutMapping("/api/user/{id}")
    public String update(UserUpdateDto userUpdateDto) {
        log.info(String.valueOf(userUpdateDto));
        return "ok";
    }
}

