package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubScribeService {

    private final SubscribeRepository subscribeRepository;


    //구독하기
    @Transactional
    public void subscribe(int fromUserId, int toUserId) {
        subscribeRepository.mSubscribe(fromUserId, toUserId);

    }

    //구독취소하기
    @Transactional
    public void unSubscribe(int fromUserId, int toUserId) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
