# :pushpin: SNS 클론 코딩
>인스타그램 클론 코딩<br>
>기존 게시판을 만들어보면서 좀 더 객체지향 적인 개발을 하고 싶다는 생각이 들어 JPA를 학습해서 사용했으며,<br>
>스프링 시큐리티와 OAuth2를 사용하여 페이스북 로그인 기능을 구현했습니다.<br>
>(데모 사이트 URL 추후 등록 예정)

</br>

## 1. 제작 기간 & 참여 인원
- 2021년 08월 ~ 진행중
- 추가 기능 구현중
- 개인 프로젝트

</br>

## 2. 사용 기술
#### `Back-end`
  - Java 11
  - Spring Boot 2.4.5
  - Maven
  - Spring Data JPA
  - MariaDB
  - Spring Security
  - OAuth2 
#### `Front-end`
  - JSP

</br>

## 3. ERD 설계
![image](https://user-images.githubusercontent.com/45502553/134459345-9dab9a1e-1d0e-456e-b005-bd242649cf28.png)


## 4. 핵심 기능
인스타그램이라는 SNS를 클론코딩하여<br>
게시글 등록/삭제/수정 기능과 구독 서비스 기능 그리고 스프링 시큐리티를 사용한 로그인 기능을 구현했습니다.<br>
메시지 및 알림 기능은 추후 구현 예정입니다.

### 4.1. 전체 흐름
![image](https://user-images.githubusercontent.com/45502553/134457600-1e0de60f-2927-40e9-8443-7a2154a2bd45.png)

### 4.2. 로그인 및 회원가입
![image](https://user-images.githubusercontent.com/45502553/134455832-68abdb7e-6c5a-4fc4-b685-857c435a644f.png)<br>
![image](https://user-images.githubusercontent.com/45502553/134455853-9d26cdcc-f495-4cdf-8f41-5c34d60d0cd9.png)


### 4.3. 프로필 페이지
- 사진 업로드
- 프로필 사진 변경
- 회원정보 변경
- 구독/구독 취소
- 구독리스트 조회

![](https://images.velog.io/images/jyo925/post/9a32e73f-76c6-4ef3-a626-00940bd661f5/image.png)<br>
![](https://images.velog.io/images/jyo925/post/5e2f0c29-8716-46b3-90a5-b690e672f297/image.png)<br>
![](https://images.velog.io/images/jyo925/post/44042597-3377-46bc-8058-14eb7ecb2633/image.png)<br>

### 4.4. 인기게시글 페이지
- 좋아요가 많은 순의 게시글 조회

![](https://images.velog.io/images/jyo925/post/a3f35b31-29ce-420c-9b4f-94ca77d6d580/image.png)

### 4.5. 스토리 페이지
- 구독한 사람들의 업로드된 게시물을 조회
- 좋아요/좋아요 취소
- 댓글 등록/삭제

![](https://images.velog.io/images/jyo925/post/0db63abe-49a0-4c39-9a4f-e7739e6786f2/image.png)
</br>
