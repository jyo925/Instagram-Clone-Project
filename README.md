# :pushpin: SNS 클론 코딩
>인스타그램 클론 코딩<br><br>
>기본적인 게시판 형식의 웹 사이트를 만들어보면서 
>좀 더 객체지향 적인 개발을 해보고 싶어서 JPA를 학습하여 사용했습니다.<br>
>스프링 시큐리티와 OAuth2를 사용하여 페이스북 로그인 기능을 구현했습니다.<br>
>이외에도 핸들러와 AOP를 사용하여 예외처리를 한 곳에서 처리하도록 구현했습니다.<br>
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
![image](https://user-images.githubusercontent.com/45502553/134460686-462c8a30-80df-4396-bec6-2e638dd12a40.png)

### 4.3. 프로필 페이지

![image](https://user-images.githubusercontent.com/45502553/134460293-5f581186-b7eb-4c83-85d3-8e83ecd51c5b.png)<br>
- 회원정보 변경
![image](https://user-images.githubusercontent.com/45502553/134460352-b2aa9de2-e819-4a7b-8522-fc99b4c5aef9.png)<br>
- 사진 업로드
![image](https://user-images.githubusercontent.com/45502553/134460425-3c33644b-2e0f-49a3-8447-03484f2ecea9.png)<br>
- 구독/구독 취소
- 구독리스트 조회
![image](https://user-images.githubusercontent.com/45502553/134460472-ca805bf2-3f0e-4ad8-9dca-e7bc16e3841f.png)

### 4.4. 인기게시글 페이지
- 좋아요가 많은 순의 게시글 조회

![image](https://user-images.githubusercontent.com/45502553/134460658-a863e8ad-ffb7-4294-8b90-11d7667e3ed8.png)

### 4.5. 스토리 페이지
- 구독한 사람들의 업로드된 게시물을 조회
- 좋아요/좋아요 취소
- 댓글 등록/삭제

![image](https://user-images.githubusercontent.com/45502553/134460583-a14de1ec-a0c4-4f7e-a10d-286b0acee75f.png)
</br>
