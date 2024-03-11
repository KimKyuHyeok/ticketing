프로젝트 소개
======

### 프로젝트 명 - 핫콘 프로젝트
### 개발 및 인원 - 1인 BE 개인 프로젝트
### 링크 - http://50.19.48.218:8080/

-----
## 사용 기술
- Java
- SpringBoot
- MySQL
- Mybatis
- JavaScript
- HTML, CSS
- Thymeleaf
- AWS EC2, AWS S3
- CKEditor

-----
ERD
-----
![스크린샷 2024-02-26 오후 1 36 42](https://github.com/KimKyuHyeok/ticketing/assets/124980807/81b5dce1-df58-4018-af76-05cb8f3dfaf7)
-----

API
-----
|API|Method|설명|
|------|---|---|
|/|GET|메인페이지|
|/login|GET|로그인 페이지|
|/login|POST|로그인 요청|
|/logout|GET|로그아웃|
|/myPage|GET|마이페이지|
|/myPage/update|POST|마이페이지 수정 요청|
|/member/check-duplicate|GET|닉네임 및 이메일 중복여부 체크|
|/join|GET|회원가입 페이지|
|/join/request|POST|회원가입 요청|
|/member/join/check-duplicate|GET|회원가입 시 정보 중복여부 체크|
|/board|GET|꿀팁게시판 페이지|
|/board/{tipId}|GET|게시글 상세페이지|
|/board/form|GET|게시글 작성페이지|
|/board/form/write|POST|게시글 작성 요청|
|/board/{tipId}/form|GET|게시글 수정 페이지|
|/board/{tipId}/form/request|POST|게시글 수정 요청|
|/board/{tipId}/delete|GET|게시글 삭제|
|/board/{tipId}/comment|POST|댓글 작성|
|/image/{imageName}|GET|이미지 가져오기|
|/image/upload|POST|이미지 업로드|
|/admin|GET|괸리자 페이지|
|/admin/concert/upload|POST|관리자 페이지 콘서트정보 업로드|
-----
