package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    //네이티브 쿼리
    //리턴 값을 int로 주면 변경된 개수만큼 리턴, 실패는 -1, 0은 아무것도 되지 않음(굳이 사용X -> 예외 처리하면 됨)
    //m은 내가 만들었다는 약어

    @Modifying //DB에 변경(insert, update, delete)을 주는 네이티브 쿼리에는 이 어노테이션이 필요
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(int fromUserId, int toUserId);


    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId);

    
}
