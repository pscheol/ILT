---
title: 2. OAuth 2.0
date: 2020-03-28 00:19:03
tags: 
- OAuth
- OAuth2.0
- Authentication
- 인증
- 보안
categories: 
- 'Computer'
- 'Security'
- 'Authentication'
- 'OAuth'
---



# OAuth 2.0

OAuth 2.0은 OAuth 1.0의 단점을 개선한 것으로, OAuth 1.0은 웹 애플리케이션이 아닌 애플리케이션에서는 사용하기 곤란하다는 단점이 있다. 그리고 절차가 복잡하여 OAuth 구현 라이브러리를 제작하기 어렵고, 복잡한 절차 때문에 Service Provider에게도 연산 부담이 발생한다.

## OAuth 1.0과 OAuth2.0의 차이

- 인증절차의 간소화로 인해 개발자가 구현하기 쉬워짐
- 기존에 사용하던 용어도 바뀌면서 Authorization Server와 Resource서버의 분리가 명시적으로 되었다.
- 다양한 인증방식을 지원

## OAuth 2.0의 특징

1. **인증절차 간소화**
   - 기능을 단순화했으며 기능과 규모의 확장성 등을 지원한다.
    - OAuth 1.0은 디지털 서명 기반이지만 OAuth 2.0의 암호화는 HTTPS에 맡김으로 복잡한 디지털 서명에 관한 로직을 요구하지 않으므로 개발이 쉬움 쉽게말해 암호화가 필요 없으며 HTTPS를 사용하고 HMAC을 사용하지 않는다.
2.  **용어변경**
    - Resource Owner : 사용자 (1,0 User해당)
    - Resource Server : 자원을 호스팅 하는 서버[REST API Server(1.0 Protected Resource)]
    - Authorization Server : 사용자의 동의를 받아 권한을 부여하는 인증서버.(API 서버와 같을 수 있다. 1.0 Service Provider) 일반적으로 Resource Server와 같은 URL 하위에 있는 경우가 많다.
    - Client : Resource Server에서 제공하는 자원을 사용하는 애플리케이션[ Third Party Application(1.0 Service Provider해당)]
4.  **Resource Server와 Authorization Server의 분리**
    - 커다란 서비스는 인증 서버로 분리하거나 다중화 할 수 있어야한다.
    - Authorization Server의 역할을 명확히 한다.
5.  **다양한 인증방식(grant_type)**
    - Authorization Code Grant
    - Implicit Grant
    - Resource Owner Password Credentials Grant
    - Client Credentials Grant
    - Device Code Grant
    - Refresh Token Grant

6.  **웹 애플리케이션이 아닌 데스크탑, 앱 애플리케이션 지원강화**
    
7. **그외**
   - Signature의 단순화 정렬과 URL 인코딩이 필요 없다.
   - Access Token 갱신 Ouath 1.0에서 Access Token을 받으면 Access Token을 계속 사용할 수 있다. OAuth2.0에서는 보안 강화를 위해 Access Token의 Life-time을 지정할 수 있도록 했다.

## 인증 종류

**OAuth 2.0은 6가지의 인증종류가 있다.**

### Authorization Code Grant

Authorization Code Grant는 일반적인 웹사이트에서 소셜로그인과 같은 인증을 받을 때 가장 많이 쓰는 방식으로 기본적으로 지원하고 있는 방식이다.


![[그림1. Authorization Code Grant type 으로 Access Token을 얻어오는 시퀀스 다이어그램]](2-OAuth-2-0-info/oauth2_flow1.png "[그림1. Authorization Code Grant type 으로 Access Token을 얻어오는 시퀀스 다이어그램]")


- `1` Authorization Request : 클라이언트가 Redirect URL을 포함하여 Authorization server 인증 요청을 한다.
- `2` User Login & Consent : Authorization Server는 유저에게 로그인창을 제공하여 유저를 인증하게 된다.
- `3` Authorization Code Response : Authorization Server는 Authorization code를 클라이언트에게 제공해준다.
- `4`, `5`, `6`, `7`, `8`, `9` :Client는 코드를 Authorization server에 Access Token을 요청한다. 그리고 Authorization 서버는 클라이언트에게 Access token을 발급해주고, Access Token을 이용하여 Resource server에 자원을 접근할 수 있게 된다.
- `10`, `11` : 토큰이 만료된다면 Refresh token을 이용하여 토큰을 재발급 받을 수 있다.

### Implicit Grant

Public Client인 브라우저 기반의 애플리케이션(Javascript application)이나 모바일 애플리케이션에서 바로 Resource Server에 접근하여 사용할 수 있는 방식이다.



![[그림2. Implicit Grant 시퀀스 다이어그램]](2-OAuth-2-0-info/oauth2_flow2.png "[그림2. Implicit Grant 시퀀스 다이어그램]")

- `1` : 클라이언트는 Authorization server에 인증을 요청한다.
- `2` : 유저는 Authorization server를 통해 인증한다.
- `3` : Authorization server는 Access token을 포함하여 클라이언트의 Redirect URL을 호출한다.
- `4` : 클라이언트는 해당 Access token이 유효한지 Authorization server에 인증요청한다.
- `5` : 인증서버는 그 토큰이 유효하다면 토큰의 만기시간과함께 리턴해준다.
- `6`, `7` : 클라이언트는 Resource server에 접근할 수 있게된다.

### Resource Owner Pasword Credentials Grant

Client에 아이디/패스워드를 받아 아이디/패스워드로 직접 access token을 받아오는 방식이다. Client가 신용이 없을 때에는 사용하기에 위험하다는 단점이 있다. 클라이언트가 확실한 신용이 보장될 때 사용할 수 있는 방식이다.


![[그림3. Resource Owner Pasword Credentials Grant 시퀀스 다이어그램]](2-OAuth-2-0-info/oauth2_flow3.png "[그림3. Resource Owner Pasword Credentials Grant 시퀀스 다이어그램]")


- `1` : User가 Id와 Password를 입력한다
- `1.1` : 클라이언트는 유저의 id와 password와 클라이언트 정보를 넘긴다.
- `1.2` : Authorization sever는 Access token을 넘긴다.

### Client Credentials Grant

애플리케이션이 Confidential Client일 때 id와 secret을 가지고 인증하는 방식이다.

{% asset_img oauth2_flow4.png  %}

![[그림4. Client Credentials Grant 시퀀스 다이어그램]](2-OAuth-2-0-info/oauth2_flow4.png "[그림4. Client Credentials Grant 시퀀스 다이어그램]")


- `1` : 클라이언트 정보를 Authorization server에 넘긴다.
- `2` : Access Token을 Client에 전달한다.

### Device Code Grant

장치 코드 부여 유형은 브라우저가 없거나 입력이 제한된 장치에서 사용됩니다.

### Refresh Token Grant

기존에 저장해둔 리프러시 토큰이 존재할 때 엑세스토큰 재발급 받을 필요가 있을 때 사용한다. 그리고 기존 액세스는 토큰이 만료된다.




## 참조

https://developers.payco.com/guide/development/start
https://d2.naver.com/helloworld/24942
https://minwan1.github.io/2018/02/24/2018-02-24-OAuth/
https://www.oauth.com/OAuth2-servers/differences-between-OAuth-1-2/
https://developer.accela.com/docs/construct-authCodeFlow.html