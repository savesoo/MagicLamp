# Genie (도서 판매 사이트)
**팀원 : @skjung1979(팀장), [@HS0430(팀장)](https://github.com/HS0430), @Seo-Aram, [@himediahj](https://github.com/himediahj), [@sangminby](https://github.com/sangminby), [@savesoo](https://github.com/savesoo)**
## Contents
1. [개요](#개요)
2. [설계의 주안점](#설계의-주안점)
3. [사용기술 및 개발환경](#사용기술-및-개발환경)
4. [프로젝트 기능 구현](#프로젝트-기능-구현)
5. [주요기능](#주요기능)
6. [Document](#document)
* * *
## 개요
- 도서 구매사이트
* * *
## 설계의 주안점
1. **Spring Security**를 이용한 로그인으로 유저 인증과 권한 검사
2. **MVC Model2** 기반 구축
3. **Restful API** 활용
4. **Open API**를 사용하여 필요한 정보 가공하여 사용
* * *
## 사용기술 및 개발환경
|Category|Detail|
|:--:|:--:|
|Frontend|HTML5, JS, CSS3, Bootstrap|
|Backend|Java, SpringBoot 2.7.6, JPA, Mybatis, Thymeleaf|
|OS|Windows10|
|Library&API|Spring Security, Validation, Lombok, j-son simple, 다음 우편번호, 알라딘|
|IDE|IntelliJ IDEA|
|Server|AWS, Tomcat|
|Document||
|CI|Github|
|DataBase|MySQL 8.0|
* * *
## 프로젝트 기능 구현
- @Seo-Aram
  - 환경 구축
  - 회원 관리
    - 로그인
    - 회원가입
- [@himediahj](https://github.com/Seo-Aram/MagicLamp/commits?author=himediahj)
  - 리뷰 기능
    - 자신이 등록한 리뷰 수정 삭제
    - 리뷰 공감 기능
  - 메인 화면 신간 리스트
    - 알라딘 Open API 사용
  - 회원가입 유효성 검사
- [@sangminby](https://github.com/Seo-Aram/MagicLamp/commits?author=sangminby)
  - 관리자 페이지
    - 관리자 권한을 가진 사람만 접근 가능하도록 구현
    - 도서 검색, 등록, 삭제
    - 도서 정보 수정
    - 도서 표지 이미지 파일 로컬에 저장
- [@skjung1979](https://github.com/Seo-Aram/MagicLamp/commits?author=skjung1979)
  - 도서 목록
    - 도서 검색
    - 도서 카테고리별 리스트 출력
  - 도서 상세페이지
  - 선택한 도서 장바구니에 담기
- [@savesoo](https://github.com/Seo-Aram/MagicLamp/commits?author=savesoo)
  - 결제하기
    - 해당 회원 정보, 주문 도서 정보 불러옴
    - 우편번호 api 사용
    - 마일리지 사용 기능
  - 메인 화면 베스트 셀러 TOP 10 
- [@HS0430](https://github.com/Seo-Aram/MagicLamp/commits?author=HS0430)
  - 마이페이지
    - 회원정보
      - 로그인한 사용자의 정보 수정 ( 패스워드, 주소, 전화번호)
      - 회원 탈퇴
    - 배송 주소록
      - 수령인으로 검색, 배송 주소 리스트 출력, 수정, 삭제
    - 마일리지
      - 적립, 사용 내역 리스트
      - 30일 이내 소멸 마일리지 표시
    - 마이 리뷰
      - 리뷰 리스트 출력, 리뷰 수정 삭제
    - 주문 내역
    - 장바구니
      - 선택 일괄 결제 가능
* * *
## 주요기능
* * *
## Document
erd랑 flow차트 삽입하면 될 듯
