package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.likes.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;

    @Transactional
    public void like(int imageId, int principalId) {
        likesRepository.mLike(imageId, principalId);
    }

    @Transactional
    public void unLike(int imageId, int principalId) {
        likesRepository.mUnLike(imageId, principalId);
    }
}
