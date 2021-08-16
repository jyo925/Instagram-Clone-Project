package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id, Model model) {
        User userEntity = userService.userProfile(id);

        model.addAttribute("user", userEntity);
        //회원 프로필 페이지는 이미지 정보뿐만 아니라 회원정보, 구독정보도 필요하다.


        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id,
                         @AuthenticationPrincipal PrincipalDetails principalDetails) {

        log.info(String.valueOf(principalDetails.getUser()));

//        model.addAttribute("principal", principalDetails.getUser());
        return "user/update";
    }
}
