# 개인 프로젝트 💻

### 🔑 Introduction

<table>
    <tr>
        <th>프로젝트명</th>
        <th>기술 프로젝트</th>
        <th>개발 기간</th>
        <th>2021.08.13 ~ 2021.08.27</th>
    </tr>
    <tr>
        <th>프로젝트 성격</th>
        <th>토이 프로젝트</th>
        <th>개발 인원</th>
        <th><a href="https://github.com/khj923265">김형준</a>	(1명)
      </th>
    </tr>
      <tr>
        <th>프로젝트 개요</th>
        <th>경험해보고 공부하자</th>
        <th>개발 도구</th>
        <th>IntelliJ IDEA</th>
    </tr>
    <tr>
        <th>front-end</th>
        <th colspan="3">HTML/CSS, JavaScript, Mustache</th>
    </tr>
    <tr>
        <th>back-end</th>
        <th colspan="3">Java11, Springboot, Spring Security, JPA</th>
    </tr>
    <tr>
        <th>DB</th>
        <th colspan="3">(AWS RDS) MySQL, Redis</th>
    </tr>
    <tr>
        <th>infra</th>
        <th colspan="3">NGINX, Apache(TomCat)</th>
    </tr>
</table>

### 주요 타겟 기술
- NGINX 를 사용해 로드밸런싱 구현
- Spring Security + JWT 를 이용한 인증, 인가 구현  
accessToken (Redis), refreshToken (MySQL) 에 저장 후 Cookie(HTTPOnly)
에 accessToken 만 저장해 만료시 재발급
- JPA QueryDsl 세팅 및 간단한 CRUD 작성 ( 딥하게 공부해야함 )
- (번외) Mustache 템플릿엔진 사용해본 걸로 😝

### Mustache 추가로 사용해본 경험 추가
가볍고 편한 만큼 너무 기능이 적어 한계가 있다고 느낌
++Handlebars.js 사용을 고려해야함

Enum 사용시 key, value 함께 사용시 아래방법으로 사용 가능
{{#enumObject}}
    {{key}}
    {{value}}
{{/enumObject}}
