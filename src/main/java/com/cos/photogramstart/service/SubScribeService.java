package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubScribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em; //Repository는 이 엔티티매니저를 구현해서 만들어져있는 구현체


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


    //구독리스트
    @Transactional(readOnly = true)
    public List<SubscribeDto> subscribeList(int principalId, int pageUserId) {

        //끝에 한 칸 띄우기 & 세미콜론X
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
        sb.append("if((SELECT TRUE FROM subscribe WHERE fromUserId = ? AND toUserId = u.id), 1, 0) subscribeState, "); //principalId
        sb.append("if((u.id = ?),1,0) equalUserState ");  //principalId
        sb.append("FROM user u INNER JOIN subscribe s ");
        sb.append("ON u.id = s.toUserId ");
        sb.append("WHERE s.fromUserId = ?"); //pageUserId

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        //쿼리 실행
        //qlrm이란? 데이터베이스에서 쿼리가 수행되고 나온 결과를 자바 클래스에 매핑해주는 라이브러리
        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);

        return subscribeDtos;
    }
}
