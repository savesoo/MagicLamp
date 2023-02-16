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
- 도서 판매 사이트를 주제로 한 페이지 제작
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
|Library&API|Spring Security, Validation, Lombok, axios, j-son simple, 다음 우편번호, 알라딘|
|IDE|IntelliJ IDEA|
|Server|AWS|
|Document|EER Diagram|
|CI|Github|
|DataBase|MySQL 8.0|
* * *
## 프로젝트 기능 구현
- [@Seo-Aram](https://github.com/Seo-Aram/MagicLamp/commits?author=Seo-Aram)
  - 환경 구축
  - 회원 관리
    - 로그인, 자동 로그인
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
  - 주문/결제하기
    - 해당 회원 정보, 주문할 도서 정보 불러옴
    - 우편번호 api 사용
    - 마일리지 사용 기능
  - 메인 화면 베스트 셀러 TOP 10 출력
- [@HS0430](https://github.com/Seo-Aram/MagicLamp/commits?author=HS0430)
  - 마이페이지
    - 회원정보
      - 로그인한 사용자의 정보 수정 ( 패스워드, 주소, 전화번호)
      - 회원 탈퇴
    - 배송 주소록
      - 수령인으로 검색, 배송 주소 리스트 출력, 수정, 삭제
      - 주문 페이지 내 배송지 선택 기능
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
**1. 메인 화면**

- 알라딘 오픈 API 를 이용한 신간 정보 불러오기
- 판매량 순위 보여주기

![MAIN](https://user-images.githubusercontent.com/113006963/218699219-98be08d7-f3cb-4c9d-a337-16be64fca8f5.jpg)

**2. 회원가입**

- ID, PW, 휴대폰 정규식 사용하여 형식에 맞는지 확인 후 형식에 맞을 경우만 가입 가능

![회원가입](https://user-images.githubusercontent.com/113006963/218700670-1cb49d6c-971c-45fa-ab9c-527b3eedee55.jpg)

**3. 도서 리스트**

- 카테고리 별 도서 보기
- 여러 상품을 선택하여 일괄 카트 담기
- 리뷰 개수 클릭 시 도서 상세페이지 내 리뷰란으로 이동
- 재고 없을시 품절 버튼 보이도록 처리

![LIST](https://user-images.githubusercontent.com/113006963/218702449-8bb56906-1b48-488a-9744-62a2a15903fd.jpg)

**4. 도서 상세페이지**

- 수량 선택하여 카트 담기, 바로 구매 가능
- 도서 구매한 사람만 리뷰 등록 가능 (본인이 작성한 리뷰 수정/삭제 가능)
- 리스트와 동일하게 재고 없을시 품절 버튼 보이도록 처리

![detail](https://user-images.githubusercontent.com/113006963/218705021-761ae0b3-d229-4d1c-8d97-cee2ab33c9b7.jpg)
![reviewdetail](https://user-images.githubusercontent.com/113006963/218706556-b0a3bf43-ba0e-4140-9036-373bdf999c89.jpg)

**5. 주문 페이지**

- 주문할 책 정보와 개수 확인
- 책 제목 클릭 시 도서 상세 페이지로 이동
- 배송지 선택 기능
- 마일리지 조회 및 사용 기능

![주문](https://user-images.githubusercontent.com/113006963/218707981-a22ac407-10ac-41fb-8849-b82961f8b26b.jpg)

**6. 마이페이지**

(1) 주문 배송 조회

![주문조회](https://user-images.githubusercontent.com/113006963/218708884-40f98706-3cee-461e-b7b1-25d7fd32528e.jpg)

(2) 장바구니
- 선택/일괄 결제 가능
- 최근 담은 순/ 상품명순 / 가격순 / 출간일 순 필터 기능

![장바구니](https://user-images.githubusercontent.com/113006963/218709327-0983badd-0e61-475b-9d84-a90b71a16dde.jpg)

(3) 배송주소록
- 배송지 등록, 수정, 삭제 가능

(4) 마일리지
- 전체 / 적립 / 사용 별 필터 기능

![마일리지](https://user-images.githubusercontent.com/113006963/218710860-ddbdaae5-2e66-4f7e-b607-d49ffa1fd09d.jpg)

**7. 관리자 페이지**

- 관리자 계정으로 로그인 시 도서 등록 / 도서 정보 수정 / 삭제 기능

![관리자](https://user-images.githubusercontent.com/113006963/218711475-a32690a7-4224-44b8-808c-b27a421d6be7.jpg)
* * *
## Document

![genie_erd](https://user-images.githubusercontent.com/113006963/218909441-fd9fc433-d91a-48d0-92f9-fb36b91e6c89.png)
