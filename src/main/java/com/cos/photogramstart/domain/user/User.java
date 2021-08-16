package com.cos.photogramstart.domain.user;

import com.cos.photogramstart.domain.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 증가 전략을 DB를 따르도록
    private int id;

    @Column(unique = true, length = 20)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website;
    private String bio; //자기소개
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl;
    private String role;

    //mappedBy란, 나는 연관관계의 주인이 아니다.
    //그러므로 테이블에 컬럼을 생성X & Usre select 시 해당 User id로 등록된 image를 모두 가져온다.
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Image> images;


    private LocalDateTime createDate;

    @PrePersist //DB에 insert 되기 직전에 실행
    private void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
