# Aptmoa
공공데이터와 카카오맵 API를 활용한 아파트 실거래가 정보 및 리뷰 제공 사이트

### 배경
힘들어진 내 집 마련 - 급격하게 오른 집값으로 이전보다 더욱 힘들어진 내 집 마련 힘들게 사는만큼 신중하게 좋은집을 구하고 싶다
아파트에 관한 모든 정보를 담은 컨버전스 웹 플랫폼

### 목적
좋은 집의 조건을 고려하여 정보를 제공한다
- 집 주변에 어떤 편의시설들이 있는지 알아본다.
- 로드뷰를 이용해 관심 지역의 실제 모습을 확인한다.

회원이 직접 투자가치나 잠재가치가 높은 지역을 선택해볼 수 있다.
- 관심 아파트 지정시에 해당 아파트의 년도별 실거래가 변화 추이를 확인할 수 있다.
- 입지, 주변상가, 학군 등을 고려해 어떤 가치를 가진 지역인지 파악한다.

### 노션 주소
https://www.notion.so/APTMOA-Springboot-Vue-e7a3ee8c53b7478e8bba3841a17cd3aa

### 요구사항 정의서
![요구사항정의서](https://user-images.githubusercontent.com/16608930/170436966-42bf9358-7c11-447e-b662-2dab0bd03186.png)

### DB-ERD
![DB설계도](https://user-images.githubusercontent.com/16608930/170437005-82b6678a-4727-40c1-bfe0-df8e45707f5f.png)

### 클래스 다이어그램
![클래스다이어그램](https://user-images.githubusercontent.com/16608930/170437115-1c0de1bb-af47-4c5c-8457-3f0fa319c6e9.png)

### 화면설계서
![화면설계서](https://user-images.githubusercontent.com/16608930/170437140-1cdd6b5c-16ad-409f-9966-5ac0bcf12196.png)

### 시스템 아키텍처
![시스템 아키텍처](https://user-images.githubusercontent.com/16608930/170437197-0ca7b2ed-4f6f-4949-a180-363b03100c0c.png)


### 프로젝트 일정
![image](https://user-images.githubusercontent.com/16608930/170436026-82d914c8-96e7-4801-a987-d21b3f7756a6.png)
![image](https://user-images.githubusercontent.com/16608930/170436069-ceb99342-bb1d-4ad1-96e4-cb786df88245.png)
<!-- ### 개인별 일정 -->

### 향후 발전계획
- 로드뷰(일부 구현이라 완성하기)

- 머신러닝을 활용한 미래 아파트 가격 예측 모델링 해보기

- 매입자 연령대별 부동산거래 조회 서비스

- 프롭테크 웹으로 거듭나도록 꾸준한 추가개발
  - 프롭테크는 부동산(Property)과 기술(Technology)의 합성어로, 부동산에 첨단 기술을 접목한 것을 뜻한다.
 
- **3D 실내공간 정보 제공**

## 개발 스크린샷

### 메인 홈 화면
![main_login](https://user-images.githubusercontent.com/16608930/170482169-d25123fd-a0b0-4546-9091-479b6ef6665b.png)

### 지역 기반, 시간 기반 검색
![apart_dong](https://user-images.githubusercontent.com/16608930/170481637-e8863c5c-eabb-4324-a985-e1c9f57e507a.png)


### 아파트 이름으로 검색
![apart_name](https://user-images.githubusercontent.com/16608930/170481542-af6970dd-ab85-4243-875a-8010bcf0cbea.png)

### 관심목록에 등록한 아파트의 실거래가 변동 추이 확인
![apart_interest_detail](https://user-images.githubusercontent.com/16608930/170481699-7234a124-beb1-44a8-b272-03ccb4285610.png)

### 키워드로 주변 인프라 검색
![map_search](https://user-images.githubusercontent.com/16608930/170481803-3feb4405-5a66-46e6-8f1d-044b7c6accac.png)

### 인프라 조회
![map_infowindow](https://user-images.githubusercontent.com/16608930/170481835-e222f2c8-5ab3-44c4-aa65-6007919dfeda.png)

### 길 찾기
![map_findload](https://user-images.githubusercontent.com/16608930/170482213-f137c5b8-aa64-4eda-85ee-35f2146ed909.png)

### 비밀번호 찾기
![findpassword](https://user-images.githubusercontent.com/16608930/170482083-5ed76f69-609f-42a7-8013-2ece6257af56.png)

### 임시 비밀번호 발급
![findpassword_email](https://user-images.githubusercontent.com/16608930/170482106-d2b38a41-771c-4a83-9b0f-f764f506cba5.png)

### 소셜 로그인
![login_kakao](https://user-images.githubusercontent.com/16608930/170482139-7db26742-3dc5-48e9-863c-17ead7f485e7.png)

### 부동산 관련 뉴스 모아보기
![news_list](https://user-images.githubusercontent.com/16608930/170482251-9ed4f991-cfed-424a-9a5d-0fa2ee1bc892.png)

### 관리자만 작성가능한 공지사항
![notice_list](https://user-images.githubusercontent.com/16608930/170482304-827b9a2c-f376-4df8-bf34-b170e912b868.png)

### 유저들의 질의응답 게시판
![qna_list](https://user-images.githubusercontent.com/16608930/170482337-46d3337b-10d1-4e43-96e3-9cf0aad301b3.png)

### 질의응답에 응답하는 덧글 기능
![qna_list](https://user-images.githubusercontent.com/16608930/170482373-a181fb33-0870-428a-9471-e35503c7a97f.png)

### 관리자의 유저관리
![admin_member_management](https://user-images.githubusercontent.com/16608930/170482443-dc3b13b1-7cf5-4a19-9991-5e1f9115e126.png)
