package com.cos.photogramstart.domain.comment;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 증가 전략을 DB를 따르도록
    private int id;

    @Column(length = 100, nullable = false)
    private String content;

    //작성자
    @JsonIgnoreProperties({"images"})
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    //어떤 이미지 게시물
    @JoinColumn(name = "imageId")
    @ManyToOne
    private Image image;

    private LocalDateTime createDate;

    @PrePersist //DB에 insert 되기 직전에 실행
    private void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
