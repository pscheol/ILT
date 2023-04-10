

# 1. OAuth란?


## 개요 
OAuth는 Open Authorization or Open Authentication이란 뜻으로 인터넷 사용자들이 비밀번호를 제공하지 않고 다른 웹 사이트 상의 자신들의 정보에 대해 웹 사이트나, 애플리케이션의 접근 권한을 부여할 수 있는 공통적인 수단으로 사용되는 접근 위임을 위한 개방형 표준이다. 구글, 페이스북, 트위터, 카카오, 네이버 등의 기업들이 타사 애플리케이션이나 웹 사이트의 계정을 공유할 수 있게 허용해준다.

> 애플리케이션 서비스 공급자들이(Service Provider) 유저의 비밀번호를 Third Party앱에 제공 없이 인증, 인가를 할 수 있는 표준 프로토콜이며, OAuth 인증을 하면 애플리케이션 API를 접근할 수 있는 권한을 얻을 수 있다.

과거에는 인증방식의 표준이 없기에 아이디와 비밀번호를 사용했고 이것은 보안상 취약한 구조를 가지고 있었다. 유저의 비밀번호가 노출될 가능성이 크기 때문이다. 이 문제를 해결하기 위해 OAuth의 인증이 등장했고 API를 제공하는 서버에서 인증을 진행하고 유저가 인증되었으면 Access Token을 발급하였다. 발급이 완료된 Access Token은 Third Party(Consumer) 애플리케이션에서 서비스 공급자(Service Provider)의 API를 안전하고 쉽게 사용할 수 있게 되었다.


## 용어

|용어|설명|
|:---:|---|
|사용자(User)|Service Provider에 계정을 가지고 있으면서, Consumer 앱을 사용하려는 사용자|
|서비스 공급자(Service Provider) | OAuth를 통해 접근을 지원하는 웹 애플리케이션(Open API를 제공하는 서비스. ex) kakao, naver, facebook, google, etc.) |
|보호 자원(Protected Resource)| 서비스 공급자(Service Provider)로부터 제공되어지는 API 자원들|
|소비자(Consumer)| Open API를 이용하여 개발된 OAuth를 사용하여 서비스 공급자의 기능을 사용하는 웹 사이트 또는 애플리케이션| 
|소비자 키(Consumer Key) | 소비자(Consumer)가 서비스 제공자(Service Provider)에게 자신임을 식별하는데 사용하기 위한 키 | 
|소비자 비밀번호(Consumer Secret) | 소비자(Consumer)가 소유권을 인정하기 위해 소비자(Consumer)가 사용하는 Secret | 
|요청 토큰(Request Token) |소비자가 사용자에게 접근권한을 인증받기 위해 필요한 정보가 담겨 있으며 후에 접근 토큰(Access token)으로 변환|
|접근 토큰(Access Token) | 인증 후 사용자가 서비스 제공자가 아닌 소비자를 통해서 보호된 자원에 접근하기 위한 키를 포함한 값|
|토큰 암호(Token Secret) | 주어진 토큰의 소유권을 인증하기 위해 소비자가 사용하는 Secret|



## OAuth 1.0의 WorkFlow


![[그림1. OAuth 1.0의 WorkFlow]](1-OAuth-Info/oauth1workflow.png "[그림1. OAuth 1.0의 WorkFlow]") 

사전에 소비자(Consumer)는 서비스 공급자(Service Provider)로부터 Client Key와 Secret을 발급 받아야한다. 이것은 서비스 공급자(Service Provider)의 API를 사용할 것을 등록하는 것과 동시에 서비스 공급자(Service Provider)가 소비자(Consumer)을 식별할 수 있게 한다.

### 처리과정

1. Consumer는 Request Token을 받기위해 Consumer 정보, Signature 정보를 `A` 처럼 Service Provider에 보내어 Request Token을 요청하고 Service Provider는 Request Token을 발급하여 `B` 처럼 Consumer에게 결과를 보낸다.

2. Request Token을 받은 Consumer는 `C` 처럼 Service Provider의 인증 사이트로 이동하여 유저는 그곳에서 Service Provider의 유저임을 인증한다.
3. 그러면 Service Provider는 `D`처럼 유저 인증이되면 Consumer에게 OAuth_token과 OAuth_verifier를 넘겨준다. 
4. Consumer는 OAuth_token과 OAuth_verifier를 받고 `E`의 흐름처럼 다시 서명을 하여 Service Provider는 Access Token을 생성을 한다.
5. 그리고 `F`처럼 다시 Consumer에게 Access Token을 전송하고 Accsss Token을 가진 Consumer는 Access Token 및 서명정보를 통해 `G` 처럼 Service Provider의 Protected Resource에 접근할 수 있다.


### Request Token 요청 매개변수

|매개변수|설명|
|:---:|---|
|oauth_callback| Service Provider가 인증을 완료한 후 리다이렉트할 Consumer의 웹 주소. 만약 Consumer가 웹 애플리케이션이 아니라 리다이렉트할 주소가 없다면 소문자로 'oob'(Out Of Band라는 뜻)를 값으로 사용한다.|
|oauth_consumer_key|Consumer를 구별하는 키 값. Service Provider는 이 키 값으로 Consumer를 구분한다.|
|oauth_nonce|Consumer에서 임시로 생성한 임의의 문자열. oauth_timestamp의 값이 같은 요청에서는 유일한 값이어야 한다. 이는 악의적인 목적으로 계속 요청을 보내는 것을 막기 위해서이다.|
|oauth_signature|OAuth 인증 정보를 암호화하고 인코딩하여 서명 값. OAuth 인증 정보는 매개변수 중에서 oauth_signature를 제외한 나머지 매개변수와 HTTP 요청 방식을 문자열로 조합한 값이다. 암화 방식은 oauth_signature_method에 정의된다.|
|oauth_signature_method|oauth_signature를 암호화하는 방법. HMAC-SHA1, HMAC-MD5 등을 사용할 수 있다.|
|oauth_timestamp|요청을 생성한 시점의 타임스탬프. 1970년1월 1일 00시 00분 00초 이후의 시간을 초로 환산한 초 단위의 누적 시간이다.|
|oauth_version| OAuth 사용버전. 1.0a는 1.0이라고 명시하면 된다.|


### oauth_signature 만들기

OAuth 1.0에서는 Service Provider에게 요청을 하려면 매번 oauth_signature 생성해야 한다. Consumer와 Service Provider가 같은 암호화(signing) 알고리즘을 이용하여 oauth_signature 만들어야 한다.

oauth_signature 다음과 같이 `네 단계`를 거쳐 만든다.

1. 요청 매개변수를 모두 모은다.
oauth_signature를 제외하고 'oauth_'로 시작하는 OAuth 관련 매개변수를 모은다. POST body에서 매개변수를 사용하고 있다면 이 매개변수도 모아야 한다.

2. 매개변수를 정규화(Normalize)한다.
모든 매개변수를 사전순으로 정렬하고 각각의 키(key)와 값(value)에 URL 인코딩(rfc3986)을 적용한다. URL 인코딩을 실시한 결과를 = 형태로 나열하고 각 쌍 사이에는 &을 넣는다. 이렇게 나온 결과 전체에 또 URL 인코딩을 적용한다.

3. Signature Base String을 만든다.
HTTP method 명(GET 또는 POST), Consumer가 호출한 HTTP URL 주소(매개변수 제외), 정규화한 매개변수를 '&'를 사용해 결합한다. 즉 '[GET|POST] + & + [URL 문자열로 매개변수는 제외] + & + [정규화한 매개변수]' 형태가 된다. 이 예제에서는 'http://nid.naver.com/naver.oauth' 을 URL로 사용하고, 이 URL에 URL 인코딩을 적용한 값을 사용했다.

4. 키 생성
3번 과정까지 거쳐 생성한 문자열을 암호화한다. 암호화할 때 Consumer Secret Key를 사용한다. Consumer Secret Key는 Consumer가 Service Provider에 사용 등록을 할 때 발급받은 값이다. HMAC-SHA1 등의 암호화 방법을 이용하여 최종적인 oauth_signature를 생성한다.


### Access Token 생성 요청

Access Token을 요청하는 방법은 Request Token을 요청하는 방법과 거의 같지만, 사용하는 매개변수의 종류가 약간 다르고 oauth_signature를 생성할 때 사용하는 키가 다르다. 
- Access Token 을 요청할 때에는 매개변수 oauth_callback는 없고, oauth_token와 oauth_verifer가 있다.
- Request Token 발급을 요청할 때에는 Consumer Secret Key를 사용해 oauth_token_secret를 생성했다.
-  Access Token 발급을 요청할 때에는 Consumer Secret Key에 oauth_token_secret을 결합한 값(Consumer Secret Key + & + oauth_token_secret)을 사용해 oauth_token_secret를 생성한다. 암호화 키를 변경하여 보안을 더 강화하는 것이다.

**Access Token발급 요청 매개변수**

|매개변수|설명|
|:---:|---|
|oauth_consumer_key| Consumer를 구별하는 키 값. Service Provider는 이 키 값으로 Consumer를 구분한다.|
|oauth_nonce| Consumer에서 임시로 생성한 임의의 문자열. oauth_timestamp의 값이 같은 요청에서는 유일한 값이어야 한다. 이는 악의적인 목적으로 계속 요청을 보내는 것을 막기 위해서이다.|
|oauth_signature|OAuth 인증 정보를 암호화하고 인코딩하여 서명 값. OAuth 인증 정보는 매개변수 중에서 oauth_signature를 제외한 나머지 매개변수와 HTTP 요청 방식을 문자열로 조합한 값이다. 암화 방식은 oauth_signature_method에 정의된다.|
|oauth_signature_method|	oauth_signature를 암호화하는 방법. HMAC-SHA1, HMAC-MD5 등을 사용할 수 있다.|
|oauth_timestamp|요청을 생성한 시점의 타임스탬프. 1970년1월 1일 00시 00분 00초 이후의 시간을 초로 환산한 초 단위의 누적 시간이다.|
|oauth_version|OAuth 사용 버전|
|oauth_verifier|Request Token 요청 시 oauth_callback으로 전달받은 oauth_verifier 값|
|oauth_token|Request Token 요청 시 oauth_callback으로 전달받은 oauth_token 값|


위의 표에 정의한 매개변수를 상황에 맞게 정의한 다음 Access Token을 요청하면 oauth_token과 oauth_token_secret을 전달받게 된다. Service Provider에 따라 사용자의 아이디나 프로필 정보 같은 것들이 반환되기도 한다.

### Acess Token 사용 

|매개변수|설명|
|:---:|---|
|oauth_consumer_key| Consumer를 구별하는 키 값. Service Provider는 이 키 값으로 Consumer를 구분한다.|
|oauth_nonce|Consumer에서 임시로 생성한 임의의 문자열. oauth_timestamp의 값이 같은 요청에서는 유일한 값이어야 한다. 이는 악의적인 목적으로 계속 요청을 보내는 것을 막기 위해서이다.|
|oauth_signature|OAuth 인증 정보를 암호화하고 인코딩하여 서명 값. OAuth 인증 정보는 매개변수 중에서 oauth_signature를 제외한 나머지 매개변수와 HTTP 요청 방식을 문자열로 조합한 값이다. 암화 방식은 oauth_signature_method에 정의된다.
|oauth_signature_method|oauth_signature를 암호화하는 방법. HMAC-SHA1, HMAC-MD5 등을 사용할 수 있다.|
|oauth_timestamp|요청을 생성한 시점의 타임스탬프. 1970년1월 1일 00시 00분 00초 이후의 시간을 초로 환산한 초 단위의 누적 시간이다.|
|oauth_version|OAuth 버전|
|oauth_token|oauth_callback으로 전달받은 oauth_token|

## 참조

https://ko.wikipedia.org/wiki/OAuth
https://oauth.net/core/1.0/
