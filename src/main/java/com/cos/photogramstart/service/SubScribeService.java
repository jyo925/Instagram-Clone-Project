package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubScribeService {

    private final SubscribeRepository subscribeRepository;


    //구독하기
    @Transactional
    public void subscribe(int fromUserId, int toUserId) {
        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독이 되어있습니다.");
        }

    }

    //구독취소하기
    @Transactional
    public void unSubscribe(int fromUserId, int toUserId) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }


    @Transactional(readOnly = true)
    public List<SubscribeDto> subscribeList(int principalId, int pageUserId) {

        return null;
    }
}
