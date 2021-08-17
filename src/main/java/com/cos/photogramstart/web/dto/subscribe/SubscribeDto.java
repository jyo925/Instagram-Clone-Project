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
    private boolean subscribeState;
    private boolean equalUserState;
}
