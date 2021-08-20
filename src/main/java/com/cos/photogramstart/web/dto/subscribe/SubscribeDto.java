package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//구독 정보 클릭시 보이는 구독유저들
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscribeDto {

    private int id;
    private String username;
    private String profileImageUrl;
    private Integer subscribeState; //Maria DB는 true값을 받으려면 Integer 타입 사용
    private Integer equalUserState;
}
