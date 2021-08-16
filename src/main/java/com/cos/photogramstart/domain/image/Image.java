package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String caption; //설명
    private String postImageUrl; //사진을 전송 받아서 서버 내 폴더에 저장할 예정, DB에는 저장 경로를 insert

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    //이미지 좋아요

    //이미지 댓글


    private LocalDateTime createDate;

    @PrePersist //DB에 insert 되기 직전에 실행
    private void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
