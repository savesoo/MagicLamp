# 매직램프 (MagicLamp_BookStore)
**팀원 : 정성균(팀장), 전현석(팀장), 서아람, 서혜정, 이상민, 정수연**
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
|Backend|Java, SpringBoot 2.7.6, Mybatis|
|OS|Windows10|
|Library&API|Spring Security, Validation, Lombok, j-son simple, |
|IDE|IntelliJ IDEA|
|Server||
|Document||
|CI|Github|
|DataBase|MySQL 8.0|
* * *
## 프로젝트 기능 구현
- 서아람
  - 환경 구축
  - 회원 관리
    - 로그인
    - 회원가입
- 서혜정
  - 리뷰 기능
  - 알라딘 Open API 사용
- 이상민
  - 관리자 페이지
    - 관리자 권한을 가진 사람만 접근 가능하도록 구현
    - 도서 등록, 삭제
    - 도서 정보 수정
    - 도서 표지 이미지 파일 로컬에 저장
- 정성균
  - 도서 목록
    - 도서 검색
    - 페이징 기능
  - 도서 상세페이지
  - 선택한 도서 카트에 담기
- 정수연
  - 주문 페이지
- 전현석
  - 마이페이지
    - 회원정보
      - 로그인한 사용자의 정보 수정 ( 패스워드, 주소, 전화번호)
      - 회원 탈퇴
    - 배송 주소록
    - 마일리지
    - 마이 리뷰
    - 주문 내역
    - 장바구니
* * *
## 주요기능
* * *
## Document
erd랑 flow차트 삽입하면 될 듯
