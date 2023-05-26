# JIT-Factory-Backend
USG 캡스톤디자인 졸업작품 백엔드 레포지토리
## Backend
### Spring Boot(3.0.4)
- Spring Boot 3.0.0 버전 이상을 사용하기 위해 Java 17 버전 필요

### Gradle
- gradle-7.6.1 사용

## yml 설정
### My-SQL
- 추후 JASYPT(yml 암호화 라이브러리) 도입하여 보안 강화 예정

### Spring Docs
- SpirngDoc-Ui의 SpringDoc를 사용해서 API 명세서 사용함
- localhost:8080/jit-factory-api.html 로 path 커스텀

### Security
- JWT
    - 모든 인증 인가는 JWT 토큰을 이용
- Redis
    - 별도의 인증 서버로 토큰 관리
- OAuth2.0
    - Frontend에서서 localhost:8080은 라우터 설정이 안되기 때문에 redirectURL을 추가해야함
    - naver : 관리자와 사용자의 유저 제한이 걸려있어 Test시 id요청 필수
    - kakao

### Mail 인증
- 일반 회원 가입시 이메일 인증을 통한 회원가입이 이루어짐
- sbs-dev@naver.com 메일로 전송됨
